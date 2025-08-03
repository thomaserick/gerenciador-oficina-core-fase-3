package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemServico;
import com.fiap.pj.core.orcamento.usecase.AlterarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.command.AlterarOrcamentoCommand;
import com.fiap.pj.core.orcamento.usecase.command.OrcamentoItemServicoCommand;
import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class AlterarOrcamentoService implements AlterarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;
    private final ClienteDomainRepository clienteDomainRepository;
    private final ServicoDomainRepository servicoDomainRepository;


    @Override
    public void handle(AlterarOrcamentoCommand cmd) {
        var orcamento = repository.findByIdOrThrowNotFound(cmd.getId());
        var cliente = clienteDomainRepository.findByIdOrThrowNotFound(cmd.getClienteId());
        cliente.validarVeiculo(cmd.getVeiculoId());

        orcamento.alterar(cmd.getDescricao(), cmd.getClienteId(), cmd.getVeiculoId(), cmd.getHodometro());
        buildItemServico(orcamento, cmd.getServicos());
        repository.save(orcamento);
    }

    private void buildItemServico(Orcamento orcamento, Set<OrcamentoItemServicoCommand> servicos) {

        orcamento.getServicos().clear();

        servicos.forEach(cmd -> {

            var service = servicoDomainRepository.findByIdOrThrowNotFound(cmd.servicoId());
            var servicoOrcamento = OrcamentoItemServico.builder().id(UUID.randomUUID())
                    .orcamentoId(orcamento.getId())
                    .preco(service.getPreco())
                    .quantidade(cmd.quantidade())
                    .descricao(service.getDescricao())
                    .servicoId(service.getId())
                    .build();
            orcamento.adicionarServico(servicoOrcamento);
        });

    }


}
