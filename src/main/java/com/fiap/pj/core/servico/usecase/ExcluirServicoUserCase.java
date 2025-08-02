package com.fiap.pj.core.servico.usecase;

import com.fiap.pj.core.servico.usecase.command.ExcluirServicoCommand;

public interface ExcluirServicoUserCase {
    void handle(ExcluirServicoCommand cmd);
}
