package com.fiap.pj.core.ordemservico.usecase.command;

import java.util.UUID;

public record CriarOrdemServicoCommand(UUID clienteId,
                                       UUID veiculoId) {

}
