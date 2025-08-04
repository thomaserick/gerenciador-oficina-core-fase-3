package com.fiap.pj.core.pecaInsumo.app;

import com.fiap.pj.core.pecaInsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.pecaInsumo.usecase.AlterarPecaInsumoUseCase;
import com.fiap.pj.core.pecaInsumo.usecase.command.AlterarPecaInsumoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AlterarPecaInsumoService implements AlterarPecaInsumoUseCase {

    private final PecaInsumoDomainRepository repository;

    @Override
    public void handle(AlterarPecaInsumoCommand cmd) {
        var pecaInsumo = repository.findByIdOrThrowNotFound(cmd.getId());
        pecaInsumo.atualizarDados(cmd.getNome(), cmd.getDescricao(), cmd.getValorUnitario());
        repository.save(pecaInsumo);
    }
} 