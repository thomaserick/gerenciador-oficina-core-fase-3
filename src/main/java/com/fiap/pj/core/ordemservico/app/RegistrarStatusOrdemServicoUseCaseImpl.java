package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.observability.app.gateways.ObservabilityGateway;
import com.fiap.pj.core.ordemservico.app.usecase.RegistrarStatusOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.command.RegistrarStatusOrdemServicoCommand;

import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class RegistrarStatusOrdemServicoUseCaseImpl implements RegistrarStatusOrdemServicoUseCase {


    private final ObservabilityGateway observabilityGateway;

    public RegistrarStatusOrdemServicoUseCaseImpl(ObservabilityGateway observabilityGateway) {
        this.observabilityGateway = observabilityGateway;
    }


    @Override
    public void handle(RegistrarStatusOrdemServicoCommand cmd) {
        var ordemServico = cmd.ordemServico();
        long duracaoMs = Duration.between(cmd.dataCriacaoStatusAnterior(), ZonedDateTime.now(ZoneOffset.UTC)).toMinutes();

        Map<String, Object> atributos = new HashMap<>();
        atributos.put("status",cmd.ordemServico().getStatus().name());
        atributos.put("duracaoMs", duracaoMs);
        atributos.put("ordemServicoId", ordemServico.getId());

        observabilityGateway.registrarEvento("OrdemServicoStatus", atributos);

    }
}
