package com.fiap.pj.core.ordemservico.adapter.in.api.request;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

import static com.fiap.pj.core.ordemservico.domain.specification.AcompanharOrdemServicoSpecification.queContenhaOrdemServicoIgualA;


@Getter
@AllArgsConstructor
public class BuscarAcompanhamentoByOrdemServicoIdRequest {

    private UUID id;

    private Pageable pageable;

    public Specification<OrdemServico> buildSpecification() {
        return Specification.allOf(queContenhaOrdemServicoIgualA(this.id));
    }

}


