package com.fiap.pj.core.ordemservico.usecase;

import com.fiap.pj.core.ordemservico.usecase.command.RealizarDiagnosticoOrdemServicoCommand;

public interface RealizarDiagnosticoOrdemServicoUseCase {

    void handle(RealizarDiagnosticoOrdemServicoCommand cmd);

}