package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.usecase.ReprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.command.ReprovarOrcamentoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@AllArgsConstructor
public class ReprovarOrcamentoService implements ReprovarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;

    @Override
    public void handle(ReprovarOrcamentoCommand cmd) {
        var orcamento = repository.findByIdOrThrowNotFound(cmd.id());
        orcamento.reprovar();
        repository.save(orcamento);
    }
}
