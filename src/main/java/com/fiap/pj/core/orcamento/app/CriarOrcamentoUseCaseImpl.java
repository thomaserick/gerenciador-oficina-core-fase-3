package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.CriarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.infra.util.security.SecurityContextUtils;

import java.util.UUID;


public class CriarOrcamentoUseCaseImpl extends OrcamentoUseCaseImpl implements CriarOrcamentoUseCase {

    private final OrcamentoGateway orcamentoGateway;
    private final ClienteGateway clienteGateway;

    public CriarOrcamentoUseCaseImpl(
            ServicoGateway servicoGateway,
            PecaInsumoGateway pecaInsumoGateway,
            OrcamentoGateway orcamentoGateway,
            ClienteGateway clienteGateway
    ) {
        super(servicoGateway, pecaInsumoGateway);

        this.orcamentoGateway = orcamentoGateway;
        this.clienteGateway = clienteGateway;

    }

    @Override
    public Orcamento handle(CriarOrcamentoCommand cmd) {

        Cliente cliente = clienteGateway.buscarPorId(cmd.getClienteId()).orElseThrow(ClienteNaoEncontradoException::new);
        cliente.validarVeiculo(cmd.getVeiculoId());

        Orcamento orcamento = Orcamento.builder()
                .id(UUID.randomUUID())
                .descricao(cmd.getDescricao())
                .clienteId(cmd.getClienteId())
                .veiculoId(cmd.getVeiculoId())
                .usuarioId(SecurityContextUtils.getUsuarioId())
                .ordemServicoId(cmd.getOrdemServicoId())
                .status(OrcamentoStatus.AGUARDANDO_APROVACAO)
                .hodometro(cmd.getHodometro())
                .build();

        super.buildItemServico(orcamento, cmd.getServicos());
        super.buildItemPecaInsumo(orcamento, cmd.getPecasInsumos());

        return this.orcamentoGateway.salvar(orcamento);
    }
}
