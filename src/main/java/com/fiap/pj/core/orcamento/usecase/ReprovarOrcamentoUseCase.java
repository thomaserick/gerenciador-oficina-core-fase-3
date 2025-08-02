package com.fiap.pj.core.orcamento.usecase;

import com.fiap.pj.core.orcamento.usecase.command.ReprovarOrcamentoCommand;

public interface ReprovarOrcamentoUseCase {

    void handle(ReprovarOrcamentoCommand cmd);
}
