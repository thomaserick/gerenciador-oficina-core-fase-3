package com.fiap.pj.core.pecaInsumo.app;

import com.fiap.pj.core.pecaInsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.pecaInsumo.usecase.ExcluirPecaInsumoUseCase;
import com.fiap.pj.core.pecaInsumo.usecase.command.ExcluirPecaInsumoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ExcluirPecaInsumoService implements ExcluirPecaInsumoUseCase {

    private final PecaInsumoDomainRepository repository;

    @Override
    public void handle(ExcluirPecaInsumoCommand cmd) {
        var pecaInsumo = repository.findByIdOrThrowNotFound(cmd.getId());
        repository.delete(pecaInsumo);
    }
} 