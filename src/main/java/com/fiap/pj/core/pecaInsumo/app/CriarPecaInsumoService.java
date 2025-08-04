package com.fiap.pj.core.pecaInsumo.app;

import com.fiap.pj.core.pecaInsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecaInsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.pecaInsumo.usecase.CriarPecaInsumoUseCase;
import com.fiap.pj.core.pecaInsumo.usecase.command.CriarPecaInsumoCommand;
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
            cmd.getNome(), 
            cmd.getDescricao(), 
            cmd.getValorUnitario(), 
            cmd.getQuantidadeEstoque()
        );
        return repository.save(pecaInsumo);
    }
} 