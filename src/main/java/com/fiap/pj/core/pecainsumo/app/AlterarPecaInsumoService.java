package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.pecainsumo.usecase.AlterarPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.usecase.command.AlterarPecaInsumoCommand;
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
        pecaInsumo.atualizarDados(cmd.getDescricao(), cmd.getModeloVeiculo(), cmd.getValorUnitario(), cmd.getQuantidadeMinimoEstoque());
        repository.save(pecaInsumo);
    }
} 