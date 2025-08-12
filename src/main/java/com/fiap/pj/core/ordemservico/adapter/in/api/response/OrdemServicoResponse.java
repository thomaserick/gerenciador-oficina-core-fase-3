package com.fiap.pj.core.ordemservico.adapter.in.api.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.orcamento.adapter.in.api.response.OrcamentoResponse.ClienteResponse;
import com.fiap.pj.core.orcamento.adapter.in.api.response.OrcamentoResponse.VeiculoResponse;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

@JsonPropertyOrder({"id", "cliente", "veiculo", "usuario", "status", "dataCriacao", "dataConclusao"})
public interface OrdemServicoResponse {

    UUID getId();

    ClienteResponse getCliente();

    VeiculoResponse getVeiculo();

    UsuarioResponse getUsuario();

    OrdemServicoStatus getStatus();

    DiagnosticoResponse getDiagnostico();

    ZonedDateTime getDataCriacao();

    ZonedDateTime getDataConclusao();

    interface UsuarioResponse {

        UUID getId();

        String getNome();
    }

    interface DiagnosticoResponse {
        UUID getId();

        String getDescricao();

        ZonedDateTime getDataCriacao();
    }
}