package com.fiap.pj.infra.ordemservico.persistence.specification;

import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.infra.ordemservico.persistence.OrdemServicoEntity;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Builder
public class OrdemServicoSpecification {

    public static final String DATA_CRIACAO = "dataCriacao";
    public static final String DATA_CONCLUSAO = "dataConclusao";
    private static final String FIELD_ORDEM_SERVICO_ID = "id";
    private static final String FIELD_CLIENTE_ID = "clienteId";
    private static final String FIELD_VEICULO_ID = "veiculoId";
    private static final String FIELD_USUARIO_ID = "usuarioId";
    private static final String FIELD_STATUS = "status";


    private final UUID id;
    private final UUID clienteId;
    private final UUID veiculoId;
    private final UUID usuarioId;
    private final OrdemServicoStatus status;
    private final ZonedDateTime dataCriacaoDe;
    private final ZonedDateTime dataCriacaoAte;
    private final ZonedDateTime dataConclusaoDe;
    private final ZonedDateTime dataConclusaoAte;

    public Specification<OrdemServicoEntity> buildSpecification() {
        Specification<OrdemServicoEntity> specs = Specification.allOf();

        if (nonNull(this.id)) {
            specs = specs.and(queContenhaOrdemServicoIgualA(this.id));
        } else {
            if (nonNull(this.clienteId)) {
                specs = specs.and(queContenhaClienteIgualA(this.clienteId));
            }

            if (nonNull(this.veiculoId)) {
                specs = specs.and(queContenhaVeiculoIgualA(this.veiculoId));
            }

            if (nonNull(this.usuarioId)) {
                specs = specs.and(queContenhaUsuarioIgualA(this.usuarioId));
            }

            if (nonNull(this.status)) {
                specs = specs.and(queContenhaStatusIgualA(this.status));
            }

            specs = buildFiltrosData(specs);


        }

        return specs;
    }

    private Specification<OrdemServicoEntity> buildFiltrosData(Specification<OrdemServicoEntity> specs) {

        if (nonNull(this.dataCriacaoDe)) {
            specs = specs.and(comDataCriacaoDe(this.dataCriacaoDe));
        }

        if (nonNull(this.dataCriacaoAte)) {
            specs = specs.and(comDataCriacaoAte(this.dataCriacaoAte));
        }

        if (nonNull(this.dataConclusaoDe)) {
            specs = specs.and(comDataConclusaoDe(this.dataConclusaoDe));
        }

        if (nonNull(this.dataConclusaoAte)) {
            specs = specs.and(comDataConclusaoAte(this.dataConclusaoAte));
        }

        return specs;
    }

    private Specification<OrdemServicoEntity> queContenhaOrdemServicoIgualA(UUID ordemServicoId) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_ORDEM_SERVICO_ID), ordemServicoId);
    }

    private Specification<OrdemServicoEntity> queContenhaClienteIgualA(UUID clienteId) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_CLIENTE_ID), clienteId);
    }

    private Specification<OrdemServicoEntity> queContenhaVeiculoIgualA(UUID veiculoId) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_VEICULO_ID), veiculoId);
    }

    private Specification<OrdemServicoEntity> queContenhaUsuarioIgualA(UUID usuarioId) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_USUARIO_ID), usuarioId);
    }

    private Specification<OrdemServicoEntity> queContenhaStatusIgualA(OrdemServicoStatus status) {
        return (root, criteriaQuery, builder) -> builder.equal(root.get(FIELD_STATUS), status);
    }

    private Specification<OrdemServicoEntity> comDataCriacaoDe(ZonedDateTime dataCriacaoDe) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(DATA_CRIACAO), dataCriacaoDe);
    }

    private Specification<OrdemServicoEntity> comDataCriacaoAte(ZonedDateTime dataCriacaoAte) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(DATA_CRIACAO), dataCriacaoAte);
    }

    private Specification<OrdemServicoEntity> comDataConclusaoDe(ZonedDateTime dataConclusaoDe) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(DATA_CONCLUSAO), dataConclusaoDe);
    }

    private Specification<OrdemServicoEntity> comDataConclusaoAte(ZonedDateTime dataConclusaoAte) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(DATA_CONCLUSAO), dataConclusaoAte);
    }

}