package com.fiap.pj.core.servico.util.factory;

import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.usecase.command.AlterarServicoCommand;
import com.fiap.pj.core.servico.usecase.command.CriarServicoCommand;

import java.math.BigDecimal;
import java.util.UUID;

public class ServicoTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String DESCRICAO = "Troca de Óleo";
    public static final BigDecimal PRECO = BigDecimal.valueOf(69.00);
    public static final String OBSERVACAO = "xpto";

    public static final String DESCRICAO_ALTERADA = "Alinhamento";
    public static final BigDecimal PRECO_ALTERADO = BigDecimal.valueOf(11.00);
    public static final String OBSERVACAO_ALTERADA = "bico cortesia";

    public static Servico umServico() {
        return new Servico(ID, DESCRICAO, PRECO, OBSERVACAO, true);
    }

    public static CriarServicoCommand umCriarServicoCommand() {
        return new CriarServicoCommand(DESCRICAO, PRECO, OBSERVACAO);
    }

    public static AlterarServicoCommand umAlterarServicoCommand(UUID id) {
        return new AlterarServicoCommand(DESCRICAO_ALTERADA, PRECO_ALTERADO, OBSERVACAO_ALTERADA, id);
    }

}
