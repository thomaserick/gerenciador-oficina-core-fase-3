package com.fiap.pj.core.ordemservico.adapter.in.api.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

@JsonPropertyOrder({"id", "clienteId", "veiculoId", "usuarioId", "status", "dataCriacao", "dataConclusao"})
public interface OrdemServicoResponse {

    UUID getId();

    UUID getClienteId();

    UUID getVeiculoId();

    UUID getUsuarioId();

    OrdemServicoStatus getStatus();

    ZonedDateTime getDataCriacao();

    ZonedDateTime getDataConclusao();
}