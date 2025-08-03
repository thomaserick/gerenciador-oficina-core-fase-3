package com.fiap.pj.core.servico.app;

import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import com.fiap.pj.core.servico.usecase.ExcluirServicoUserCase;
import com.fiap.pj.core.servico.usecase.command.ExcluirServicoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ExcluirServicoService implements ExcluirServicoUserCase {

    private final ServicoDomainRepository repository;

    @Override
    public void handle(ExcluirServicoCommand cmd) {
        var servico = repository.findByIdOrThrowNotFound(cmd.id());
        repository.delete(servico);
    }
}
