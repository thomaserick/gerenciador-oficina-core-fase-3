package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.usecase.ReprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.command.ReprovarOrcamentoCommand;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ReprovarOrcamentoService extends OrcamentoService implements ReprovarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;

    public ReprovarOrcamentoService(
            ServicoDomainRepository servicoDomainRepository,
            PecaInsumoDomainRepository pecaInsumoDomainRepository, OrcamentoDomainRepository repository
    ) {
        super(servicoDomainRepository, pecaInsumoDomainRepository);
        this.repository = repository;
    }

    @Override
    public void handle(ReprovarOrcamentoCommand cmd) {
        Orcamento orcamento = this.repository.findByIdOrThrowNotFound(cmd.id());

        orcamento.reprovar();

        super.roolbackPecasInsumos(orcamento);

        this.repository.save(orcamento);
    }
}
