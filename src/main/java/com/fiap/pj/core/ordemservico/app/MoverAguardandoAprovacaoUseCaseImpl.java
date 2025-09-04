package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.AlterarStatusOsAguardandoAprovacaoUseCase;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoNaoEncontradaException;

import java.util.UUID;


public class MoverAguardandoAprovacaoUseCaseImpl implements AlterarStatusOsAguardandoAprovacaoUseCase {

    private final OrdemServicoGateway ordemServicoGateway;

    public MoverAguardandoAprovacaoUseCaseImpl(OrdemServicoGateway ordemServicoGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
    }

    @Override
    public void handle(UUID id) {
        OrdemServico ordemServico = this.ordemServicoGateway.buscarPorId(id).orElseThrow(OrdemServicoNaoEncontradaException::new);
        ordemServico.moverAguardandoAprovacao();
        this.ordemServicoGateway.salvar(ordemServico);
    }
}