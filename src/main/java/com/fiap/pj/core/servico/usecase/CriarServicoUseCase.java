package com.fiap.pj.core.servico.usecase;

import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.usecase.command.CriarServicoCommand;

public interface CriarServicoUseCase {

    Servico handle(CriarServicoCommand cmd);
}
