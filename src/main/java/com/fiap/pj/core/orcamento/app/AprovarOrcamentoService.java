package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.usecase.AprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.command.AprovarOrcamentoCommand;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.OrdemServicoDomainRepository;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.infra.security.UserDetailsServiceImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@Transactional
@AllArgsConstructor
public class AprovarOrcamentoService implements AprovarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;
    private final OrdemServicoDomainRepository ordemServicoRepository;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public void handle(AprovarOrcamentoCommand cmd) {
        Orcamento orcamento = this.repository.findByIdOrThrowNotFound(cmd.id());
        orcamento.aprovar();

        if (isNull(orcamento.getOrdemServicoId())) {
            Usuario usuario = this.userDetailsService.loadUsuarioFromSecurityContext();

            OrdemServico ordemServico = OrdemServico.builder()
                    .clienteId(orcamento.getClienteId())
                    .veiculoId(orcamento.getVeiculoId())
                    .usuarioId(usuario.getId())
                    .build();

            this.ordemServicoRepository.save(ordemServico);

            orcamento.vincularOrdemServico(ordemServico.getId());
        }

        this.repository.save(orcamento);
    }
}