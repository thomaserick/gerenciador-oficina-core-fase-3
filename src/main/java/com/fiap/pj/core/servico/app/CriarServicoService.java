package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import com.fiap.pj.core.servico.usecase.CriarServicoUseCase;
import com.fiap.pj.core.servico.usecase.command.CriarServicoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CriarServicoService implements CriarServicoUseCase {

    private final ServicoDomainRepository repository;

    @Override
    public Servico handle(CriarServicoCommand cmd) {
        var service = new Servico(UUID.randomUUID(), cmd.getDescricao(), cmd.getPreco(), cmd.getObservacao(), true);
        return repository.save(service);
    }
}
