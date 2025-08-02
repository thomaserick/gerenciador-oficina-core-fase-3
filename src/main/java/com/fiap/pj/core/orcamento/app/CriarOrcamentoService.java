package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemServico;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.orcamento.usecase.CriarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.command.CriarOrcamentoCommand;
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
public class CriarOrcamentoService implements CriarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;
    private final ClienteDomainRepository clienteDomainRepository;
    private final ServicoDomainRepository servicoDomainRepository;


    @Override
    public Orcamento handle(CriarOrcamentoCommand cmd) {

        var cliente = clienteDomainRepository.findByIdOrThrowNotFound(cmd.getClienteId());
        cliente.validarVeiculo(cmd.getVeiculoId());

        var orcamento = Orcamento.builder()
                .id(UUID.randomUUID())
                .descricao(cmd.getDescricao())
                .clienteId(cmd.getClienteId())
                .veiculoId(cmd.getVeiculoId())
                .status(OrcamentoStatus.AGUARDANDO_APROVACAO)
                .hodometro(cmd.getHodometro())
                .build();

        buildItemServico(orcamento, cmd.getServicos());

        return repository.save(orcamento);
    }

    private void buildItemServico(Orcamento orcamento, Set<OrcamentoItemServicoCommand> servicos) {

        servicos.stream().forEach(cmd -> {

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
