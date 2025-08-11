package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.OrdemServicoDomainRepository;
import com.fiap.pj.core.ordemservico.usecase.MoverAguardandoAprovacaoUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class MoverAguardandoRetiradaService implements MoverAguardandoAprovacaoUseCase {

    private final OrdemServicoDomainRepository ordemServicoRepository;

    @Override
    public void handle(UUID id) {
        OrdemServico ordemServico = this.ordemServicoRepository.findByIdOrThrowNotFound(id);
        ordemServico.moverAguardandoRetirada();

        this.ordemServicoRepository.save(ordemServico);
    }
}