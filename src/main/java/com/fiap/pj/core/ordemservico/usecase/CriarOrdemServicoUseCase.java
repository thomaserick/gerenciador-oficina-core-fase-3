package com.fiap.pj.core.ordemservico.usecase;

import com.fiap.pj.core.ordemservico.usecase.command.CriarOrdemServicoCommand;

import java.util.UUID;

public interface CriarOrdemServicoUseCase {

    UUID handle(CriarOrdemServicoCommand cmd);
}
