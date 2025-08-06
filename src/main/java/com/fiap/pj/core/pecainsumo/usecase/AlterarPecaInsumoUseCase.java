package com.fiap.pj.core.pecainsumo.usecase;

import com.fiap.pj.core.pecainsumo.usecase.command.AlterarPecaInsumoCommand;

public interface AlterarPecaInsumoUseCase {

    void handle(AlterarPecaInsumoCommand cmd);
} 