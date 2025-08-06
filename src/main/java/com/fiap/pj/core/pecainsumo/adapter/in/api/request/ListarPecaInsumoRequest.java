package com.fiap.pj.core.pecainsumo.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public class ListarPecaInsumoRequest {

    private String modeloVeiculo;
    private String descricao;
    private Boolean estoqueBaixo;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    public Specification<PecaInsumo> buildSpecification() {
        Specification<PecaInsumo> specs = Specification.allOf();

        if (hasText(this.modeloVeiculo)) {
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("modeloVeiculo")), "%" + this.modeloVeiculo.toLowerCase() + "%"));
        }

        if (hasText(this.descricao)) {
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + this.descricao.toLowerCase() + "%"));
        }

        if (this.estoqueBaixo != null && this.estoqueBaixo) {
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("quantidadeEstoque"), root.get("quantidadeMinimoEstoque")));
        }

        return specs;
    }
} 