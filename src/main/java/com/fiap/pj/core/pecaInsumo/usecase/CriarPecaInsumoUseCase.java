package com.fiap.pj.core.pecaInsumo.usecase;

import com.fiap.pj.core.pecaInsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecaInsumo.usecase.command.CriarPecaInsumoCommand;

public interface CriarPecaInsumoUseCase {

    PecaInsumo handle(CriarPecaInsumoCommand cmd);
} 