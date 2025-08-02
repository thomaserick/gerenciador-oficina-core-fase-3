package com.fiap.pj.core.servico.usecase;

import com.fiap.pj.core.servico.usecase.command.AlterarServicoCommand;

public interface AlterarServicoUseCase {

    void handle(AlterarServicoCommand cmd);
}
