package com.fiap.pj.core.servico.usecase;

import com.fiap.pj.core.servico.usecase.command.InativarServicoCommand;

public interface InativarServicoUserCase {
    void handle(InativarServicoCommand cmd);
}
