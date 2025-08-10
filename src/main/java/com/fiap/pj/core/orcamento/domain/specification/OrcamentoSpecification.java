package com.fiap.pj.core.orcamento.domain.specification;

import com.fiap.pj.core.orcamento.domain.Orcamento;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrcamentoSpecification {

    private static final String FIELD_CLIENTE_ID = "clienteId";


    public static Specification<Orcamento> queContenhaClienteIgualA(UUID clienteId) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_CLIENTE_ID), clienteId);
    }
}