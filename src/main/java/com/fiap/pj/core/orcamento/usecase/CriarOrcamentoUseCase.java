package com.fiap.pj.core.orcamento.usecase;

import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.usecase.command.CriarOrcamentoCommand;

public interface CriarOrcamentoUseCase {

    Orcamento handle(CriarOrcamentoCommand cmd);
}
