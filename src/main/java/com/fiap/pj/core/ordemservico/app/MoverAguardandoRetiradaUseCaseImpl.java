package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.MoverAguardandoRetiradaUseCase;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoNaoEncontradaException;

import java.util.UUID;


public class MoverAguardandoRetiradaUseCaseImpl implements MoverAguardandoRetiradaUseCase {

    private final OrdemServicoGateway ordemServicoGateway;

    public MoverAguardandoRetiradaUseCaseImpl(OrdemServicoGateway ordemServicoGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
    }

    @Override
    public void handle(UUID id) {
        OrdemServico ordemServico = this.ordemServicoGateway.buscarPorId(id).orElseThrow(OrdemServicoNaoEncontradaException::new);
        ordemServico.moverAguardandoRetirada();
        this.ordemServicoGateway.salvar(ordemServico);
    }
}