package com.fiap.pj.core.observability.app.gateways;


import java.util.Map;

public interface ObservabilityGateway {

    void registrarEvento(String evento, Map<String, Object> atributos);
}
