package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.OrdemServicoDomainRepository;
import com.fiap.pj.core.ordemservico.usecase.CriarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.usecase.command.CriarOrdemServicoCommand;
import com.fiap.pj.core.util.security.SecurityContextUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class CriarOrdemServicoService implements CriarOrdemServicoUseCase {

    private final OrdemServicoDomainRepository ordemServicoRepository;

    @Override
    public UUID handle(CriarOrdemServicoCommand cmd) {
        
        OrdemServico ordemServico = OrdemServico.builder()
                .clienteId(cmd.clienteId())
                .veiculoId(cmd.veiculoId())
                .usuarioId(SecurityContextUtils.getUsuarioId())
                .build();

        this.ordemServicoRepository.save(ordemServico);
        return ordemServico.getId();
    }
}
