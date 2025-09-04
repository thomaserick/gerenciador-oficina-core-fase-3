package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.MoverFinalizadaUseCase;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoNaoEncontradaException;

import java.util.UUID;

public class MoverFinalizadaUseCaseImpl implements MoverFinalizadaUseCase {

    private final OrdemServicoGateway ordemServicoGateway;

    public MoverFinalizadaUseCaseImpl(OrdemServicoGateway ordemServicoGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
    }

    @Override
    public void handle(UUID id) {
        OrdemServico ordemServico = this.ordemServicoGateway.buscarPorId(id).orElseThrow(OrdemServicoNaoEncontradaException::new);
        ordemServico.moverFinalizada();

        this.ordemServicoGateway.salvar(ordemServico);
    }
}