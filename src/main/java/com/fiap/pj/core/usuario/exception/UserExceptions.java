package com.fiap.pj.core.usuario.exception;

public class UserExceptions {
    private UserExceptions() {
    }

    public static class UsuarioNotFoundException extends RuntimeException {
        public UsuarioNotFoundException() {
            super("Usuário não encontrado.");
        }
    }
}
