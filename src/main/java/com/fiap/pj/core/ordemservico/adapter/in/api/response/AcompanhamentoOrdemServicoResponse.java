package com.fiap.pj.core.ordemservico.adapter.in.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.ordemservico.adapter.in.api.response.OrdemServicoResponse.UsuarioResponse;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@JsonPropertyOrder({"id", "acompanhamento"})
public interface AcompanhamentoOrdemServicoResponse {

    UUID getId();

    @JsonProperty("acompanhamento")
    Collection<SituacaoResponse> getHistoricoSituacao();

    @JsonPropertyOrder({"id", "status", "usuario", "dataCriacao"})
    interface SituacaoResponse {
        UUID getId();

        OrdemServicoStatus getStatus();

        UsuarioResponse getUsuario();

        ZonedDateTime getDataCriacao();

    }

}