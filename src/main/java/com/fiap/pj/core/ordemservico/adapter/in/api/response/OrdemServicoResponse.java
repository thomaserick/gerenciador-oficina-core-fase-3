package com.fiap.pj.core.ordemservico.adapter.in.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.orcamento.adapter.in.api.response.OrcamentoResponse.ClienteResponse;
import com.fiap.pj.core.orcamento.adapter.in.api.response.OrcamentoResponse.VeiculoResponse;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemPecaInsumo;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@JsonPropertyOrder({"id", "cliente", "veiculo", "usuario", "status", "diagnostico", "servicos", "pecasInsumos", "valorTotal", "dataCriacao", "dataConclusao"})
public interface OrdemServicoResponse {

    UUID getId();

    ClienteResponse getCliente();

    VeiculoResponse getVeiculo();

    UsuarioResponse getUsuario();

    OrdemServicoStatus getStatus();

    DiagnosticoResponse getDiagnostico();

    ZonedDateTime getDataCriacao();

    ZonedDateTime getDataConclusao();


    @JsonIgnore
    Collection<Orcamento> getOrcamentos();

    default Collection<ItensResponse> getServicos() {
        return this.getOrcamentos().stream().map(Orcamento::getServicos)
                .flatMap(Collection::stream)
                .map(ItensResponse::new).toList();
    }

    default Collection<ItensResponse> getPecasInsumos() {
        return this.getOrcamentos().stream().map(Orcamento::getPecasInsumos)
                .flatMap(Collection::stream)
                .map(ItensResponse::new).toList();
    }

    default BigDecimal getValorTotal() {
        return this.getOrcamentos().stream()
                .map(Orcamento::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @JsonPropertyOrder({"id", "descricao", "preco", "quantidade"})
    record ItensResponse
            (UUID id,
             String descricao,
             BigDecimal preco,
             int getQuantidade) {

        ItensResponse(OrcamentoItemServico servico) {
            this(servico.getId(), servico.getDescricao(), servico.getPreco(), servico.getQuantidade());
        }

        ItensResponse(OrcamentoItemPecaInsumo pecaInsumo) {
            this(pecaInsumo.getId(), pecaInsumo.getDescricao(), pecaInsumo.getPreco(), pecaInsumo.getQuantidade());
        }
    }


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