package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.ReprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.ReprovarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;


public class ReprovarOrcamentoUseCaseImpl extends OrcamentoUseCaseImpl implements ReprovarOrcamentoUseCase {

    private final OrcamentoGateway orcamentoGateway;

    public ReprovarOrcamentoUseCaseImpl(
            ServicoGateway servicoGateway,
            PecaInsumoGateway pecaInsumoGateway, OrcamentoGateway orcamentoGateway
    ) {
        super(servicoGateway, pecaInsumoGateway);
        this.orcamentoGateway = orcamentoGateway;
    }

    @Override
    public void handle(ReprovarOrcamentoCommand cmd) {
        Orcamento orcamento = this.orcamentoGateway.buscarPorId(cmd.id()).orElseThrow(OrcamentoNaoEncontradoException::new);
        orcamento.reprovar();

        super.roolbackPecasInsumos(orcamento);

        this.orcamentoGateway.salvar(orcamento);
    }
}
