package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.pecainsumo.usecase.CriarPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.usecase.command.CriarPecaInsumoCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CriarPecaInsumoService implements CriarPecaInsumoUseCase {

    private final PecaInsumoDomainRepository repository;

    @Override
    public PecaInsumo handle(CriarPecaInsumoCommand cmd) {
        var pecaInsumo = new PecaInsumo(
                UUID.randomUUID(),
                cmd.getModeloVeiculo(),
                cmd.getDescricao(),
                cmd.getValorUnitario(),
                cmd.getQuantidadeEstoque(),
                cmd.getQuantidadeMinimoEstoque()
        );
        return repository.save(pecaInsumo);
    }
} 