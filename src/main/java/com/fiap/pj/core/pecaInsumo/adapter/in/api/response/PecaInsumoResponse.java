package com.fiap.pj.core.pecaInsumo.adapter.in.api.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "nome", "descricao", "valorUnitario", "quantidadeEstoque", "quantidadeMinimoEstoque"})
public interface PecaInsumoResponse {

    String getId();

    String getNome();

    String getDescricao();

    String getValorUnitario();

    String getQuantidadeEstoque();
    
    String getQuantidadeMinimoEstoque();
} 