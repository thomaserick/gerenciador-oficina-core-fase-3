package com.fiap.pj.core.orcamento.usecase;

import com.fiap.pj.core.orcamento.usecase.command.AlterarOrcamentoCommand;

public interface AlterarOrcamentoUseCase {

    void handle(AlterarOrcamentoCommand cmd);
}
