package com.fiap.pj.infra.config;

import com.fiap.pj.core.observability.app.gateways.ObservabilityGateway;
import com.fiap.pj.infra.observability.gateways.NewRelicObservabilityGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewRelicObservabilityConfig {

    @Bean
    ObservabilityGateway observabilityGateways() {
        return new NewRelicObservabilityGatewayImpl();
    }

}
