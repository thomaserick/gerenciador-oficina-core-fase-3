package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.AprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.AprovarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import com.fiap.pj.core.ordemservico.app.usecase.CriarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.command.CriarOrdemServicoCommand;

import java.util.UUID;

import static java.util.Objects.isNull;


public class AprovarOrcamentoUseCaseImpl implements AprovarOrcamentoUseCase {

    private final OrcamentoGateway orcamentoGateway;
    private final CriarOrdemServicoUseCase criarOrdemServicoUseCase;

    public AprovarOrcamentoUseCaseImpl(OrcamentoGateway orcamentoGateway, CriarOrdemServicoUseCase criarOrdemServicoUseCase) {
        this.orcamentoGateway = orcamentoGateway;
        this.criarOrdemServicoUseCase = criarOrdemServicoUseCase;
    }

    @Override
    public void handle(AprovarOrcamentoCommand cmd) {
        Orcamento orcamento = this.orcamentoGateway.buscarPorId(cmd.id()).orElseThrow(OrcamentoNaoEncontradoException::new);
        orcamento.aprovar();

        if (isNull(orcamento.getOrdemServicoId())) {
            UUID ordemServicoId = this.criarOrdemServicoUseCase.handle(
                    new CriarOrdemServicoCommand(
                            orcamento.getClienteId(),
                            orcamento.getVeiculoId()
                    )
            );

            orcamento.vincularOrdemServico(ordemServicoId);
        }

        this.orcamentoGateway.salvar(orcamento);
    }
}