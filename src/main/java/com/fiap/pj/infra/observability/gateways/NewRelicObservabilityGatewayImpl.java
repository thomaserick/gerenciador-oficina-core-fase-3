package com.fiap.pj.infra.observability.gateways;

import com.fiap.pj.core.observability.app.gateways.ObservabilityGateway;
import com.newrelic.api.agent.NewRelic;

import java.util.Map;

public class NewRelicObservabilityGatewayImpl implements ObservabilityGateway {

    @Override
    public void registrarEvento(String evento, Map<String, Object> atributos) {
        NewRelic.getAgent()
                .getInsights()
                .recordCustomEvent(evento, atributos);
    }
}
