package com.fiap.pj.core.usuario.exception;

public class UsuarioExceptions {
    private UsuarioExceptions() {
    }

    public static class UsuarioNaoEncontradoException extends RuntimeException {
        public UsuarioNaoEncontradoException() {
            super("Usuário não encontrado.");
        }
    }
}
