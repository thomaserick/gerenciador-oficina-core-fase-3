package com.fiap.pj.core.pecaInsumo.usecase.command;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ExcluirPecaInsumoCommand {

    private final UUID id;

    public ExcluirPecaInsumoCommand(UUID id) {
        this.id = id;
    }
} 