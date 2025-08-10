package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.orcamento.usecase.CriarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import com.fiap.pj.core.util.security.SecurityContextUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Transactional
public class CriarOrcamentoService extends OrcamentoService implements CriarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;
    private final ClienteDomainRepository clienteDomainRepository;

    public CriarOrcamentoService(
            ServicoDomainRepository servicoDomainRepository,
            PecaInsumoDomainRepository pecaInsumoDomainRepository,
            OrcamentoDomainRepository repository,
            ClienteDomainRepository clienteDomainRepository
    ) {
        super(servicoDomainRepository, pecaInsumoDomainRepository);

        this.repository = repository;
        this.clienteDomainRepository = clienteDomainRepository;
    }

    @Override
    public Orcamento handle(CriarOrcamentoCommand cmd) {

        Cliente cliente = this.clienteDomainRepository.findByIdOrThrowNotFound(cmd.getClienteId());
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

        return this.repository.save(orcamento);
    }
}
