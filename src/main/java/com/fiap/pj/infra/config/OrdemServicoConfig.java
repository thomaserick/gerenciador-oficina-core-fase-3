package com.fiap.pj.infra.config;


import com.fiap.pj.core.ordemservico.app.BuscarAcompanhamentoByOrdemServicoIdUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.CriarOrdemServicoUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.ListarOrdemServicoUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.MoverAguardandoAprovacaoUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.MoverAguardandoRetiradaUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.MoverEmDiagnosticoUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.MoverEmExecucaoUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.MoverEntregueUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.MoverFinalizadaUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.RealizarDiagnosticoUseCaseImpl;
import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.AlterarStatusOsAguardandoAprovacaoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.BuscarAcompanhamentoByOrdemServicoIdUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.CriarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.ListarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverAguardandoRetiradaUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverEmDiagnosticoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverEmExecucaoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverEntregueUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverFinalizadaUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.RealizarDiagnosticoUseCase;
import com.fiap.pj.infra.ordemservico.gateways.OrdemServicoRepositoryGatewayImpl;
import com.fiap.pj.infra.ordemservico.persistence.OrdemServicoRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdemServicoConfig {

    @Bean
    CriarOrdemServicoUseCase criarOrdemServicoUseCase(OrdemServicoGateway ordemServicoGateway
    ) {
        return new CriarOrdemServicoUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    AlterarStatusOsAguardandoAprovacaoUseCase alterarStatusOsAguardandoAprovacaoUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new MoverAguardandoAprovacaoUseCaseImpl(ordemServicoGateway);
    }


    @Bean
    MoverAguardandoRetiradaUseCase moverAguardandoRetiradaUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new MoverAguardandoRetiradaUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    MoverEmDiagnosticoUseCase moverEmDiagnosticoUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new MoverEmDiagnosticoUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    MoverEmExecucaoUseCase moverEmExecucaoUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new MoverEmExecucaoUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    MoverEntregueUseCase moverEntregueUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new MoverEntregueUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    MoverFinalizadaUseCase moverFinalizadaUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new MoverFinalizadaUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    RealizarDiagnosticoUseCase realizarDiagnosticoUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new RealizarDiagnosticoUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    ListarOrdemServicoUseCase listarOrdemServicoUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new ListarOrdemServicoUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    BuscarAcompanhamentoByOrdemServicoIdUseCase buscarAcompanhamentoByOrdemServicoIdUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new BuscarAcompanhamentoByOrdemServicoIdUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    OrdemServicoGateway ordemServicoGateway(OrdemServicoRepositoryJpa ordemServicoRepositoryJpa) {
        return new OrdemServicoRepositoryGatewayImpl(ordemServicoRepositoryJpa);
    }

}
