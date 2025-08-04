package com.fiap.pj.core.pecaInsumo.usecase;

import com.fiap.pj.core.pecaInsumo.usecase.command.ExcluirPecaInsumoCommand;

public interface ExcluirPecaInsumoUseCase {

    void handle(ExcluirPecaInsumoCommand cmd);
} 