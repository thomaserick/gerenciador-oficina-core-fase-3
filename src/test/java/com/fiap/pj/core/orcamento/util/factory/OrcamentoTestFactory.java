package com.fiap.pj.core.orcamento.util.factory;

import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemServico;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.orcamento.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.usecase.command.OrcamentoItemServicoCommand;
import com.fiap.pj.core.orcamento.usecase.command.ReprovarOrcamentoCommand;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class OrcamentoTestFactory {


    public static final UUID ID = UUID.randomUUID();
    public static final UUID ITEM_SERVICO_ID = UUID.randomUUID();
    public static final BigDecimal ITEM_SERVICO_QUANTIDADE = BigDecimal.ONE;
    public static final UUID CLIENTE_ID = ClienteTestFactory.ID;
    public static final UUID VEICULO_ID = VeiculoTestFactory.ID;
    public static final String DESCRICAO = "Verificar Freios";
    public static final int HODOMENTO = 21052024;
    public static final OrcamentoStatus ORCAMENTO_STATUS = OrcamentoStatus.AGUARDANDO_APROVACAO;

    public static Orcamento umOrcamento() {
        return umOrcamento(ORCAMENTO_STATUS);
    }

    public static Orcamento umOrcamentoAprovado() {
        return umOrcamento(OrcamentoStatus.APROVADO);
    }

    public static Orcamento umOrcamento(OrcamentoStatus status) {
        return Orcamento.builder().id(ID)
                .status(status)
                .descricao(DESCRICAO)
                .hodometro(HODOMENTO)
                .veiculoId(VEICULO_ID)
                .clienteId(CLIENTE_ID).build();
    }


    public static OrcamentoItemServico umOrcamentoItemServico(UUID orcamentoId) {
        return new OrcamentoItemServico(ITEM_SERVICO_ID, ServicoTestFactory.ID, orcamentoId, ServicoTestFactory.DESCRICAO, ServicoTestFactory.PRECO, ITEM_SERVICO_QUANTIDADE);
    }

    public static CriarOrcamentoCommand umCriarOrcamentoCommand() {
        return new CriarOrcamentoCommand(DESCRICAO, CLIENTE_ID, VEICULO_ID, HODOMENTO, Set.of(umOrcamentoItemServicoCommand()));
    }

    public static OrcamentoItemServicoCommand umOrcamentoItemServicoCommand() {
        return new OrcamentoItemServicoCommand(ITEM_SERVICO_ID, ITEM_SERVICO_QUANTIDADE);
    }

    public static ReprovarOrcamentoCommand umReprovarOrcamentoCommand() {
        return new ReprovarOrcamentoCommand(ID);
    }

}
