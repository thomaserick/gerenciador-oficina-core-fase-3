package com.fiap.pj.core.ordemservico.app.usecase.command;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;

import java.time.ZonedDateTime;

public record RegistrarStatusOrdemServicoCommand(OrdemServico ordemServico, ZonedDateTime  dataCriacaoStatusAnterior) {
}
