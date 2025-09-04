package com.fiap.pj.infra.config;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.orcamento.app.AlterarOrcamentoUseCaseImpl;
import com.fiap.pj.core.orcamento.app.AprovarOrcamentoUseCaseImpl;
import com.fiap.pj.core.orcamento.app.CriarOrcamentoUseCaseImpl;
import com.fiap.pj.core.orcamento.app.ListarOrcamentoUseCaseImpl;
import com.fiap.pj.core.orcamento.app.ReprovarOrcamentoUseCaseImpl;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.AlterarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.AprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.CriarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.ListarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.ReprovarOrcamentoUseCase;
import com.fiap.pj.core.ordemservico.usecase.CriarOrdemServicoUseCase;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.infra.orcamento.gateways.OrcamentoRepositoryGatewayImpl;
import com.fiap.pj.infra.orcamento.persistence.OrcamentoRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrcamentoConfig {

    @Bean
    CriarOrcamentoUseCase criarOrcamentoUseCase(OrcamentoGateway orcamentoGateway, ServicoGateway servicoGateway,
                                                ClienteGateway clienteGateway, PecaInsumoGateway pecaInsumoGateway
    ) {
        return new CriarOrcamentoUseCaseImpl(servicoGateway, pecaInsumoGateway, orcamentoGateway, clienteGateway);
    }

    @Bean
    AlterarOrcamentoUseCase alterarOrcamentoUseCase(OrcamentoGateway orcamentoGateway, ServicoGateway servicoGateway,
                                                    ClienteGateway clienteGateway, PecaInsumoGateway pecaInsumoGateway) {
        return new AlterarOrcamentoUseCaseImpl(servicoGateway, pecaInsumoGateway, orcamentoGateway, clienteGateway);
    }

    @Bean
    AprovarOrcamentoUseCase aprovarOrcamentoUseCase(OrcamentoGateway orcamentoGateway, CriarOrdemServicoUseCase criarOrdemServicoUseCase) {
        return new AprovarOrcamentoUseCaseImpl(orcamentoGateway, criarOrdemServicoUseCase);
    }

    @Bean
    ReprovarOrcamentoUseCase reprovarOrcamentoUseCase(ServicoGateway servicoGateway, PecaInsumoGateway pecaInsumoGateway, OrcamentoGateway orcamentoGateway) {
        return new ReprovarOrcamentoUseCaseImpl(servicoGateway, pecaInsumoGateway, orcamentoGateway);
    }

    @Bean
    ListarOrcamentoUseCase listarOrcamentoUseCase(OrcamentoGateway orcamentoGateway) {
        return new ListarOrcamentoUseCaseImpl(orcamentoGateway);
    }

    @Bean
    OrcamentoGateway orcamentoGateway(OrcamentoRepositoryJpa orcamentoRepositoryJpa) {
        return new OrcamentoRepositoryGatewayImpl(orcamentoRepositoryJpa);
    }

}
