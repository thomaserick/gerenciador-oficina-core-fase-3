package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.usecase.AlterarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.command.AlterarOrcamentoCommand;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AlterarOrcamentoService extends OrcamentoService implements AlterarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;
    private final ClienteGateway clienteGateway;

    public AlterarOrcamentoService(
            ServicoDomainRepository servicoDomainRepository,
            PecaInsumoDomainRepository pecaInsumoDomainRepository,
            OrcamentoDomainRepository repository,
            ClienteGateway clienteGateway
    ) {
        super(servicoDomainRepository, pecaInsumoDomainRepository);

        this.repository = repository;
        this.clienteGateway = clienteGateway;

    }

    @Override
    public void handle(AlterarOrcamentoCommand cmd) {
        Orcamento orcamento = this.repository.findByIdOrThrowNotFound(cmd.getId());
        Cliente cliente = clienteGateway.buscarPorId(cmd.getClienteId()).orElseThrow(ClienteNaoEncontradoException::new);

        cliente.validarVeiculo(cmd.getVeiculoId());

        orcamento.alterar(cmd.getDescricao(), cmd.getClienteId(), cmd.getVeiculoId(), cmd.getHodometro());

        super.buildItemServico(orcamento, cmd.getServicos());
        super.buildItemPecaInsumo(orcamento, cmd.getPecasInsumos());

        this.repository.save(orcamento);
    }
}