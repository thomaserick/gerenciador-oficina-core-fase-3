package com.fiap.pj.core.usuario.exception;

public class UsuarioExceptions {
    private UsuarioExceptions() {
    }

    public static class UsuarioNotFoundException extends RuntimeException {
        public UsuarioNotFoundException() {
            super("Usuário não encontrado.");
        }
    }
}
