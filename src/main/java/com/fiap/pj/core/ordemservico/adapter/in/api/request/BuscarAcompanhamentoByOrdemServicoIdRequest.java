package com.fiap.pj.core.ordemservico.adapter.in.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;


@Getter
@AllArgsConstructor
public class BuscarAcompanhamentoByOrdemServicoIdRequest {

    private UUID id;

}


