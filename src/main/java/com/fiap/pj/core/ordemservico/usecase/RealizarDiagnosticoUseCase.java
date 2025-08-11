package com.fiap.pj.core.ordemservico.usecase;

import com.fiap.pj.core.ordemservico.usecase.command.RealizarDiagnosticoOrdemServicoCommand;

public interface RealizarDiagnosticoUseCase {

    void handle(RealizarDiagnosticoOrdemServicoCommand cmd);

}