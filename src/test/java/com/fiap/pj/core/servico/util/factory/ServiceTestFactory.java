package com.fiap.pj.core.servico.util.factory;

import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.usecase.command.CriarServicoCommand;

import java.math.BigDecimal;
import java.util.UUID;

public class ServiceTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String DESCRICAO = "Troca de Óleo";
    public static final BigDecimal PRECO = BigDecimal.valueOf(69.00);
    public static final String OBSERVACAO = "xpto";

    public static Servico oneService() {
        return new Servico(ID, DESCRICAO, PRECO, OBSERVACAO, true);
    }

    public static CriarServicoCommand oneCreateServiceCommand() {
        return new CriarServicoCommand(DESCRICAO, PRECO, OBSERVACAO);
    }

}
