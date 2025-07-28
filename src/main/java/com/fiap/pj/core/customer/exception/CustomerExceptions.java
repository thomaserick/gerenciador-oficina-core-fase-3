package com.fiap.pj.core.customer.exception;

public class CustomerExceptions {
    private CustomerExceptions() {
    }

    public static class CustomerNotFoundException extends RuntimeException {
        public CustomerNotFoundException() {
            super("Cliente não encontrado.");
        }
    }

    public static class DocumentIdentificationDuplicateException extends RuntimeException {
        public DocumentIdentificationDuplicateException() {
            super("Documento de Identificacão Duplicado.");
        }
    }
}
