package com.fiap.pj.core.orcamento.adapter.in.api.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@JsonPropertyOrder({"id", "descricao", "cliente", "veiculo", "hodometro", "status", "servicos", "pecasInsumos", "valorTotal", "dataCriacao"})
public interface OrcamentoResponse {

    UUID getId();

    String getDescricao();

    @JsonIgnore
    UUID getClienteId();

    @JsonIgnore
    String getClienteNome();

    @JsonProperty("id")
    String getVeiculoId();

    @JsonIgnore
    String getPlaca();

    @JsonIgnore
    String getModelo();

    @JsonIgnore
    String getMarca();

    @JsonIgnore
    Integer getAno();

    @JsonProperty("cliente")
    default ClienteResponse getClienteResponse() {
        return new ClienteResponse(getClienteId(), getClienteNome());
    }

    @JsonProperty("veiculo")
    default VeiculoResponse getVeiculoResponse() {
        return new VeiculoResponse(getVeiculoId(), getPlaca(), getModelo(), getMarca(), getAno());
    }

    Collection<OrcamentoServicoResponse> getServicos();

    Collection<OrcamentoPecaInsumoResponse> getPecasInsumos();

    int getHodometro();

    OrcamentoStatus getStatus();

    ZonedDateTime getDataCriacao();

    default BigDecimal getValorTotal() {
        var totalPecasInsumo = this.getPecasInsumos().stream().map(pecaInsumo -> pecaInsumo.getPreco().multiply(pecaInsumo.getQuantidade()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return this.getServicos().stream().map(servico -> servico.getPreco().multiply(servico.getQuantidade()))
                .reduce(BigDecimal.ZERO, BigDecimal::add).add(totalPecasInsumo);
    }

    record ClienteResponse(
            @JsonProperty("id")
            UUID clienteId,
            @JsonProperty("nome")
            String clienteNome) {
    }

    record VeiculoResponse(
            @JsonProperty("id")
            String veiculoId, String placa, String modelo, String marca, Integer ano) {
    }

    @JsonPropertyOrder({"id", "descricao", "preco", "quantidade"})
    interface OrcamentoServicoResponse {

        UUID getId();

        String getDescricao();

        BigDecimal getPreco();

        BigDecimal getQuantidade();
    }

    @JsonPropertyOrder({"id", "descricao", "preco", "quantidade"})
    interface OrcamentoPecaInsumoResponse {

        UUID getId();

        String getDescricao();

        BigDecimal getPreco();

        BigDecimal getQuantidade();
    }

}


