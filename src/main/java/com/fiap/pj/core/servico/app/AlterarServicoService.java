package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import com.fiap.pj.core.servico.usecase.AlterarServicoUseCase;
import com.fiap.pj.core.servico.usecase.command.AlterarServicoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AlterarServicoService implements AlterarServicoUseCase {

    private final ServicoDomainRepository repository;

    @Override
    public void handle(AlterarServicoCommand cmd) {
        var servico = repository.findByIdOrThrowNotFound(cmd.getId());
        servico.alterar(cmd.getDescricao(), cmd.getPreco(), cmd.getObservacao());
        repository.save(servico);
    }
}
