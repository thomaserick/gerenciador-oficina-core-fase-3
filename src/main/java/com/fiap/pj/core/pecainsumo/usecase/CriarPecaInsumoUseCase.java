package com.fiap.pj.core.pecainsumo.usecase;

import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.usecase.command.CriarPecaInsumoCommand;

public interface CriarPecaInsumoUseCase {

    PecaInsumo handle(CriarPecaInsumoCommand cmd);
} 