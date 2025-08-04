package com.fiap.pj.core.pecaInsumo.util.factory;

import com.fiap.pj.core.pecaInsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecaInsumo.usecase.command.AlterarPecaInsumoCommand;
import com.fiap.pj.core.pecaInsumo.usecase.command.CriarPecaInsumoCommand;

import java.math.BigDecimal;
import java.util.UUID;

public class PecaInsumoTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String NOME = "Óleo do Motor";
    public static final String DESCRICAO = "Óleo sintético 5W-30 para motor";
    public static final BigDecimal VALOR_UNITARIO = new BigDecimal("45.50");
    public static final Integer QUANTIDADE_ESTOQUE = 10;

    public static final String ALTER_NOME = "Filtro de Ar";
    public static final String ALTER_DESCRICAO = "Filtro de ar do motor";
    public static final BigDecimal ALTER_VALOR_UNITARIO = new BigDecimal("25.00");
    public static final Integer ALTER_QUANTIDADE_ESTOQUE = 15;

    public static PecaInsumo onePecaInsumo() {
        return new PecaInsumo(ID, NOME, DESCRICAO, VALOR_UNITARIO, QUANTIDADE_ESTOQUE);
    }

    public static CriarPecaInsumoCommand oneCreatePecaInsumoCommand() {
        return new CriarPecaInsumoCommand(NOME, DESCRICAO, VALOR_UNITARIO, QUANTIDADE_ESTOQUE);
    }

    public static AlterarPecaInsumoCommand umUpdatePecaInsumoCommand(UUID id) {
        return new AlterarPecaInsumoCommand(ALTER_NOME, ALTER_DESCRICAO, ALTER_VALOR_UNITARIO, ALTER_QUANTIDADE_ESTOQUE);
    }
} 