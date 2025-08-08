package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
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
    private final ClienteDomainRepository clienteDomainRepository;

    public AlterarOrcamentoService(ServicoDomainRepository servicoDomainRepository, PecaInsumoDomainRepository pecaInsumoDomainRepository,
                                   OrcamentoDomainRepository repository, ClienteDomainRepository clienteDomainRepository) {
        super(servicoDomainRepository, pecaInsumoDomainRepository);
        this.repository = repository;
        this.clienteDomainRepository = clienteDomainRepository;
    }

    @Override
    public void handle(AlterarOrcamentoCommand cmd) {
        Orcamento orcamento = this.repository.findByIdOrThrowNotFound(cmd.getId());
        Cliente cliente = this.clienteDomainRepository.findByIdOrThrowNotFound(cmd.getClienteId());

        cliente.validarVeiculo(cmd.getVeiculoId());

        orcamento.alterar(cmd.getDescricao(), cmd.getClienteId(), cmd.getVeiculoId(), cmd.getHodometro());

        super.buildItemServico(orcamento, cmd.getServicos());
        super.buildItemPecaInsumo(orcamento, cmd.getPecasInsumos());

        this.repository.save(orcamento);
    }

}
