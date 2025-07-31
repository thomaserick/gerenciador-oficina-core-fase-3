package com.fiap.pj.core.service.exception;

public class ServiceExceptions {
    private ServiceExceptions() {
    }

    public static class ServiceNotFoundException extends RuntimeException {
        public ServiceNotFoundException() {
            super("Servico não encontrado.");
        }
    }
}
