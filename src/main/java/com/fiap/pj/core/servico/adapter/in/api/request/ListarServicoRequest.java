package com.fiap.pj.core.servico.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.servico.domain.Servico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.core.servico.domain.specification.ServicoSpecification.queContenhaAtivoIgualA;
import static com.fiap.pj.core.servico.domain.specification.ServicoSpecification.queContenhaNomeCom;
import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public class ListarServicoRequest {


    private String nome;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    private Boolean ativo;

    public Specification<Servico> buildSpecification() {
        Specification<Servico> specs = Specification.allOf();

        if (hasText(this.nome)) {
            specs = specs.and(queContenhaNomeCom(this.nome));
        }

        if (Objects.nonNull(ativo)) {
            specs = specs.and(queContenhaAtivoIgualA(this.ativo));
        }
        return specs;
    }

}
