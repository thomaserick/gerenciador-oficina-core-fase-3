package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.observability.app.gateways.ObservabilityGateway;
import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.CriarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.command.CriarOrdemServicoCommand;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.infra.util.security.SecurityContextUtils;

import java.util.Map;
import java.util.UUID;


public class CriarOrdemServicoUseCaseImpl implements CriarOrdemServicoUseCase {

    private final OrdemServicoGateway ordemServicoGateway;
    private final ObservabilityGateway observabilityGateway;

    public CriarOrdemServicoUseCaseImpl(OrdemServicoGateway ordemServicoGateway, ObservabilityGateway observabilityGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
        this.observabilityGateway = observabilityGateway;
    }

    @Override
    public UUID handle(CriarOrdemServicoCommand cmd) {

        OrdemServico ordemServico = OrdemServico.builder()
                .id(UUID.randomUUID())
                .clienteId(cmd.clienteId())
                .veiculoId(cmd.veiculoId())
                .usuarioId(SecurityContextUtils.getUsuarioId())
                .status(OrdemServicoStatus.CRIADA)
                .build();

        observabilityGateway.registrarEvento("OrdemServicoCriada", Map.of("ordemServicoId", ordemServico.getId().toString(),"status", ordemServico.getStatus().name()));

        this.ordemServicoGateway.salvar(ordemServico);
        return ordemServico.getId();
    }
}
