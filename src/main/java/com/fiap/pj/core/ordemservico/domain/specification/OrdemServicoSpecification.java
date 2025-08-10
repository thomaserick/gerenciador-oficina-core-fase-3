package com.fiap.pj.core.ordemservico.domain.specification;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdemServicoSpecification {

    private static final String FIELD_ORDEM_SERVICO_ID = "id";

    private static final String FIELD_CLIENTE_ID = "clienteId";
    private static final String FIELD_VEICULO_ID = "veiculoId";
    private static final String FIELD_USUARIO_ID = "usuarioId";
    private static final String FIELD_STATUS = "status";
    public static final String DATA_CRIACAO = "dataCriacao";
    public static final String DATA_CONCLUSAO = "dataConclusao";

    public static Specification<OrdemServico> queContenhaOrdemServicoIgualA(UUID ordemServicoId) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_ORDEM_SERVICO_ID), ordemServicoId);
    }

    public static Specification<OrdemServico> queContenhaClienteIgualA(UUID clienteId) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_CLIENTE_ID), clienteId);
    }

    public static Specification<OrdemServico> queContenhaVeiculoIgualA(UUID veiculoId) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_VEICULO_ID), veiculoId);
    }

    public static Specification<OrdemServico> queContenhaUsuarioIgualA(UUID usuarioId) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_USUARIO_ID), usuarioId);
    }

    public static Specification<OrdemServico> queContenhaStatusIgualA(OrdemServicoStatus status) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_STATUS), status);
    }

    public static Specification<OrdemServico> comDataCriacaoDe(ZonedDateTime dataCriacaoDe) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(DATA_CRIACAO), dataCriacaoDe);
    }

    public static Specification<OrdemServico> comDataCriacaoAte(ZonedDateTime dataCriacaoAte) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(DATA_CRIACAO), dataCriacaoAte);
    }

    public static Specification<OrdemServico> comDataConclusaoDe(ZonedDateTime dataConclusaoDe) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(DATA_CONCLUSAO), dataConclusaoDe);
    }

    public static Specification<OrdemServico> comDataConclusaoAte(ZonedDateTime dataConclusaoAte) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(DATA_CONCLUSAO), dataConclusaoAte);
    }
}