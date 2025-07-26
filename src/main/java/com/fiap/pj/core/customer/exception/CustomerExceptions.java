package com.fiap.pj.core.customer.exception;

public class CustomerExceptions {
    private CustomerExceptions() {
    }

    public static class CustomerNotFoundException extends RuntimeException {
        public CustomerNotFoundException() {
            super("Cliente não encontrado.");
        }
    }
}
