package com.fiap.pj.infra.cliente.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.infra.cliente.persistence.ClienteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.infra.cliente.persistence.specification.ClienteSpecification.queContenhaAtivoIgualA;
import static com.fiap.pj.infra.cliente.persistence.specification.ClienteSpecification.queContenhaDocumentoIdentificacaoCom;
import static com.fiap.pj.infra.cliente.persistence.specification.ClienteSpecification.queContenhaNomeCom;
import static com.fiap.pj.infra.cliente.persistence.specification.ClienteSpecification.queContenhaPlacaIgualA;
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

    public Specification<ClienteEntity> buildSpecification() {
        Specification<ClienteEntity> specs = Specification.allOf();

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
