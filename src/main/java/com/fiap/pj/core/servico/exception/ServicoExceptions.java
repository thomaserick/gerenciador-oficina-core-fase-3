package com.fiap.pj.core.servico.exception;

public class ServicoExceptions {
    private ServicoExceptions() {
    }

    public static class ServiceNotFoundException extends RuntimeException {
        public ServiceNotFoundException() {
            super("Servico não encontrado.");
        }
    }
}
