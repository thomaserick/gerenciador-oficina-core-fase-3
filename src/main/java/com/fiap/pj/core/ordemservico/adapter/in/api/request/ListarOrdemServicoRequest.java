package com.fiap.pj.core.ordemservico.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;
import java.util.UUID;

import static com.fiap.pj.core.ordemservico.domain.specification.OrdemServicoSpecification.comDataConclusaoAte;
import static com.fiap.pj.core.ordemservico.domain.specification.OrdemServicoSpecification.comDataConclusaoDe;
import static com.fiap.pj.core.ordemservico.domain.specification.OrdemServicoSpecification.comDataCriacaoAte;
import static com.fiap.pj.core.ordemservico.domain.specification.OrdemServicoSpecification.comDataCriacaoDe;
import static com.fiap.pj.core.ordemservico.domain.specification.OrdemServicoSpecification.queContenhaClienteIgualA;
import static com.fiap.pj.core.ordemservico.domain.specification.OrdemServicoSpecification.queContenhaOrdemServicoIgualA;
import static com.fiap.pj.core.ordemservico.domain.specification.OrdemServicoSpecification.queContenhaStatusIgualA;
import static com.fiap.pj.core.ordemservico.domain.specification.OrdemServicoSpecification.queContenhaUsuarioIgualA;
import static com.fiap.pj.core.ordemservico.domain.specification.OrdemServicoSpecification.queContenhaVeiculoIgualA;
import static java.util.Objects.nonNull;

@Getter
@AllArgsConstructor
public class ListarOrdemServicoRequest {

    private UUID id;
    private UUID clienteId;
    private UUID veiculoId;
    private UUID usuarioId;
    private OrdemServicoStatus status;
    private ZonedDateTime dataCriacaoDe;
    private ZonedDateTime dataCriacaoAte;
    private ZonedDateTime dataConclusaoDe;
    private ZonedDateTime dataConclusaoAte;

    @Setter
    @JsonIgnore
    private Pageable pageable;

    public Specification<OrdemServico> buildSpecification() {
        Specification<OrdemServico> specs = Specification.allOf();

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

    private Specification<OrdemServico> buildFiltrosData(Specification<OrdemServico> specs) {

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

}