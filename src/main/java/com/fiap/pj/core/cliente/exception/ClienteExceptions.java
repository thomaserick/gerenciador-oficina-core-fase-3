package com.fiap.pj.core.cliente.exception;

public class ClienteExceptions {
    private ClienteExceptions() {
    }

    public static class ClienteNaoEncontradoException extends RuntimeException {
        public ClienteNaoEncontradoException() {
            super("Cliente não encontrado.");
        }
    }

    public static class DocumentoIdentificacaoDuplicadoException extends RuntimeException {
        public DocumentoIdentificacaoDuplicadoException() {
            super("Documento de Identificacão Duplicado.");
        }
    }
}
