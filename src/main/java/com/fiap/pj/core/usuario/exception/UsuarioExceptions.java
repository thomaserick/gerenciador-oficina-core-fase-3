package com.fiap.pj.core.usuario.exception;

public class UsuarioExceptions {

    public class UsuarioNotFoundException extends RuntimeException {
        public UsuarioNotFoundException() {
            super("Usuário não encontrado.");
        }

    }
}
