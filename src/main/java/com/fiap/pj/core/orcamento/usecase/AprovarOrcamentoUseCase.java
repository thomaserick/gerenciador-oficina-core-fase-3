package com.fiap.pj.core.orcamento.usecase;

import com.fiap.pj.core.orcamento.usecase.command.AprovarOrcamentoCommand;

public interface AprovarOrcamentoUseCase {

    void handle(AprovarOrcamentoCommand cmd);
}
