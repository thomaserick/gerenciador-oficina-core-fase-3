package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.OrdemServicoDomainRepository;
import com.fiap.pj.core.ordemservico.usecase.MoverEntregueUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class MoverEntregueService implements MoverEntregueUseCase {

    private final OrdemServicoDomainRepository ordemServicoRepository;

    @Override
    public void handle(UUID id) {
        OrdemServico ordemServico = this.ordemServicoRepository.findByIdOrThrowNotFound(id);
        ordemServico.moverEntregue();

        this.ordemServicoRepository.save(ordemServico);
    }
}