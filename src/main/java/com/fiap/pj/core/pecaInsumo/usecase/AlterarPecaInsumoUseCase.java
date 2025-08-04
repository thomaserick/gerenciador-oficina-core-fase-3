package com.fiap.pj.core.pecaInsumo.usecase;

import com.fiap.pj.core.pecaInsumo.usecase.command.AlterarPecaInsumoCommand;

public interface AlterarPecaInsumoUseCase {

    void handle(AlterarPecaInsumoCommand cmd);
} 