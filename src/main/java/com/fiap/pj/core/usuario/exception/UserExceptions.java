package com.fiap.pj.core.usuario.exception;

public class UserExceptions {
    private UserExceptions() {
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException() {
            super("Usuário não encontrado.");
        }
    }
}
