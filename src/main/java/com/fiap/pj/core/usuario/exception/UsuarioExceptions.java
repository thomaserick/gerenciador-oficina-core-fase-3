package com.fiap.pj.core.usuario.exception;

public class UsuarioExceptions {
    private UsuarioExceptions() {
    }

    public static class UserNaoEncontradoException extends RuntimeException {
        public UserNaoEncontradoException() {
            super("Usuário não encontrado.");
        }
    }
}
