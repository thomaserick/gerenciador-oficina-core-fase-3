package com.fiap.pj.core.pecainsumo.usecase;

import com.fiap.pj.core.pecainsumo.usecase.command.ExcluirPecaInsumoCommand;

public interface ExcluirPecaInsumoUseCase {

    void handle(ExcluirPecaInsumoCommand cmd);
} 