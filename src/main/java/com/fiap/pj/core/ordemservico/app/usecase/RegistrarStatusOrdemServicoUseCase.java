package com.fiap.pj.core.ordemservico.app.usecase;

import com.fiap.pj.core.ordemservico.app.usecase.command.RegistrarStatusOrdemServicoCommand;

public interface RegistrarStatusOrdemServicoUseCase {

    void handle(RegistrarStatusOrdemServicoCommand cmd);
}
