package com.fiap.pj.core.servico.usecase;

import com.fiap.pj.core.servico.usecase.command.AtivarServicoCommand;

public interface AtivarServicoUserCase {
    void handle(AtivarServicoCommand cmd);
}
