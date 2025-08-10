package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.OrdemServicoDomainRepository;
import com.fiap.pj.core.ordemservico.usecase.RealizarDiagnosticoOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.usecase.command.RealizarDiagnosticoOrdemServicoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class RealizarDiagnosticoOrdemServicoService implements RealizarDiagnosticoOrdemServicoUseCase {

    private final OrdemServicoDomainRepository ordemServicoDomainRepository;

    @Override
    public void handle(RealizarDiagnosticoOrdemServicoCommand cmd) {
        OrdemServico ordemServico = this.ordemServicoDomainRepository.findByIdOrThrowNotFound(cmd.getOrdemServicoId());
        ordemServico.realizarDiagnostico(cmd.getDescricao());
        this.ordemServicoDomainRepository.save(ordemServico);
    }
}