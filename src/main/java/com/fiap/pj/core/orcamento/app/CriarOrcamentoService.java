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
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.infra.security.UserDetailsServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Transactional
public class CriarOrcamentoService extends OrcamentoServico implements CriarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;
    private final ClienteDomainRepository clienteDomainRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public CriarOrcamentoService(ServicoDomainRepository servicoDomainRepository, PecaInsumoDomainRepository pecaInsumoDomainRepository,
                                 OrcamentoDomainRepository repository, ClienteDomainRepository clienteDomainRepository, UserDetailsServiceImpl userDetailsService) {
        super(servicoDomainRepository, pecaInsumoDomainRepository);
        this.repository = repository;
        this.clienteDomainRepository = clienteDomainRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Orcamento handle(CriarOrcamentoCommand cmd) {

        Cliente cliente = this.clienteDomainRepository.findByIdOrThrowNotFound(cmd.getClienteId());
        cliente.validarVeiculo(cmd.getVeiculoId());

        Usuario usuario = this.userDetailsService.loadUsuarioFromSecurityContext();

        Orcamento orcamento = Orcamento.builder()
                .id(UUID.randomUUID())
                .descricao(cmd.getDescricao())
                .clienteId(cmd.getClienteId())
                .veiculoId(cmd.getVeiculoId())
                .usuarioId(usuario.getId())
                .ordemServicoId(cmd.getOrdemServicoId())
                .status(OrcamentoStatus.AGUARDANDO_APROVACAO)
                .hodometro(cmd.getHodometro())
                .build();

        super.buildItemServico(orcamento, cmd.getServicos());
        super.buildItemPecaInsumo(orcamento, cmd.getPecasInsumos());

        return this.repository.save(orcamento);
    }
}
