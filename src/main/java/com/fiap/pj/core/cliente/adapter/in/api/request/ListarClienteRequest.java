package com.fiap.pj.core.cliente.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.cliente.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.core.cliente.domain.specification.ClienteSpecification.queContenhaAtivoIgualA;
import static com.fiap.pj.core.cliente.domain.specification.ClienteSpecification.queContenhaDocumentoIdentificacaoCom;
import static com.fiap.pj.core.cliente.domain.specification.ClienteSpecification.queContenhaNomeCom;
import static com.fiap.pj.core.cliente.domain.specification.ClienteSpecification.queContenhaPlacaIgualA;
import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public class ListarClienteRequest {

    private String nome;
    private String documentoIdentificacao;
    private String placa;
    
    @Setter
    @JsonIgnore
    private Pageable pageable;

    private Boolean ativo;

    public Specification<Cliente> buildSpecification() {
        Specification<Cliente> specs = Specification.allOf();

        if (hasText(this.nome)) {
            specs = specs.and(queContenhaNomeCom(this.nome));
        }

        if (hasText(this.documentoIdentificacao)) {
            specs = specs.and(queContenhaDocumentoIdentificacaoCom(this.documentoIdentificacao));
        }

        if (hasText(this.placa)) {
            specs = specs.and(queContenhaPlacaIgualA(this.placa));
        }

        if (Objects.nonNull(ativo)) {
            specs = specs.and(queContenhaAtivoIgualA(this.ativo));
        }
        return specs;
    }

}
