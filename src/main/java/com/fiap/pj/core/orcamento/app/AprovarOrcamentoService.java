package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.usecase.AprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.command.AprovarOrcamentoCommand;
import com.fiap.pj.core.ordemservico.usecase.CriarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.usecase.command.CriarOrdemServicoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@Transactional
@AllArgsConstructor
public class AprovarOrcamentoService implements AprovarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;
    private final CriarOrdemServicoUseCase criarOrdemServicoUseCase;

    @Override
    public void handle(AprovarOrcamentoCommand cmd) {
        Orcamento orcamento = this.repository.findByIdOrThrowNotFound(cmd.id());
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

        this.repository.save(orcamento);
    }
}