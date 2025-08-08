package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemPecaInsumo;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemServico;
import com.fiap.pj.core.orcamento.usecase.command.OrcamentoItemPecaInsumoCommand;
import com.fiap.pj.core.orcamento.usecase.command.OrcamentoItemServicoCommand;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public abstract class OrcamentoService {

    private final ServicoDomainRepository servicoDomainRepository;
    private final PecaInsumoDomainRepository pecaInsumoDomainRepository;

    protected void buildItemPecaInsumo(Orcamento orcamento, Set<OrcamentoItemPecaInsumoCommand> pecasInsumos) {
        orcamento.getPecasInsumos().clear();

        pecasInsumos.forEach(cmd -> {
            PecaInsumo pecaInsumo = this.pecaInsumoDomainRepository.findByIdOrThrowNotFound(cmd.pecaInsumoId());

            OrcamentoItemPecaInsumo pecaInsumoOrcamento = OrcamentoItemPecaInsumo
                    .builder()
                    .id(UUID.randomUUID())
                    .pecasInsumosId(pecaInsumo.getId())
                    .orcamentoId(orcamento.getId())
                    .quantidade(cmd.quantidade())
                    .descricao(pecaInsumo.getDescricao())
                    .preco(pecaInsumo.getValorUnitario())
                    .build();

            orcamento.adicionaPecaInsumo(pecaInsumoOrcamento);
        });
    }

    protected void buildItemServico(Orcamento orcamento, Set<OrcamentoItemServicoCommand> servicos) {
        orcamento.getServicos().clear();

        servicos.forEach(cmd -> {

            Servico servico = this.servicoDomainRepository.findByIdOrThrowNotFound(cmd.servicoId());

            OrcamentoItemServico servicoOrcamento = OrcamentoItemServico
                    .builder()
                    .id(UUID.randomUUID())
                    .orcamentoId(orcamento.getId())
                    .preco(servico.getPreco())
                    .quantidade(cmd.quantidade())
                    .descricao(servico.getDescricao())
                    .servicoId(servico.getId())
                    .build();

            orcamento.adicionarServico(servicoOrcamento);
        });
    }
}