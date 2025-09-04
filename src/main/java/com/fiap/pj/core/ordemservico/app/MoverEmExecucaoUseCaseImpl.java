package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.MoverEmExecucaoUseCase;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoNaoEncontradaException;

import java.util.UUID;

public class MoverEmExecucaoUseCaseImpl implements MoverEmExecucaoUseCase {

    private final OrdemServicoGateway ordemServicoGateway;

    public MoverEmExecucaoUseCaseImpl(OrdemServicoGateway ordemServicoGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
    }

    @Override
    public void handle(UUID id) {
        OrdemServico ordemServico = this.ordemServicoGateway.buscarPorId(id).orElseThrow(OrdemServicoNaoEncontradaException::new);
        ordemServico.moverEmExecucao();

        this.ordemServicoGateway.salvar(ordemServico);
    }
}