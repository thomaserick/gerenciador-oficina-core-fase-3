package com.fiap.pj.core.servico.app;

import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import com.fiap.pj.core.servico.usecase.InativarServicoUserCase;
import com.fiap.pj.core.servico.usecase.command.InativarServicoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class InativarServicoService implements InativarServicoUserCase {

    private final ServicoDomainRepository repository;

    @Override
    public void handle(InativarServicoCommand cmd) {
        var servico = repository.findByIdOrThrowNotFound(cmd.id());
        servico.inativar();
        repository.save(servico);
    }
}
