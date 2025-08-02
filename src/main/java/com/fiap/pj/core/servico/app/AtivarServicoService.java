package com.fiap.pj.core.servico.app;

import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import com.fiap.pj.core.servico.usecase.AtivarServicoUserCase;
import com.fiap.pj.core.servico.usecase.command.AtivarServicoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AtivarServicoService implements AtivarServicoUserCase {

    private final ServicoDomainRepository repository;

    @Override
    public void handle(AtivarServicoCommand cmd) {
        var servico = repository.findByIdOrThrowNotFound(cmd.id());
        servico.ativar();
        repository.save(servico);
    }
}
