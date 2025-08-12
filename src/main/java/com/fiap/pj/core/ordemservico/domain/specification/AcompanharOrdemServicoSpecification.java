package com.fiap.pj.core.ordemservico.domain.specification;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AcompanharOrdemServicoSpecification {

    private static final String FIELD_ORDEM_SERVICO_ID = "id";

    public static Specification<OrdemServico> queContenhaOrdemServicoIgualA(UUID ordemServicoId) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ORDEM_SERVICO_ID), ordemServicoId);
    }

}