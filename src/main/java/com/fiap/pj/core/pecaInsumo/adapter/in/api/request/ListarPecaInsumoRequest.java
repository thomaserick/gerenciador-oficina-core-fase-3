package com.fiap.pj.core.pecaInsumo.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.pecaInsumo.domain.PecaInsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public class ListarPecaInsumoRequest {

    private String nome;
    private String descricao;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    public Specification<PecaInsumo> buildSpecification() {
        Specification<PecaInsumo> specs = Specification.allOf();

        if (hasText(this.nome)) {
            specs = specs.and((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + this.nome.toLowerCase() + "%"));
        }

        if (hasText(this.descricao)) {
            specs = specs.and((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + this.descricao.toLowerCase() + "%"));
        }

        return specs;
    }
} 