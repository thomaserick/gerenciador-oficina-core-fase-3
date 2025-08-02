package com.fiap.pj.core.servico.exception;

public class ServicoExceptions {
    private ServicoExceptions() {
    }

    public static class ServicoNaoEncontradoException extends RuntimeException {
        public ServicoNaoEncontradoException() {
            super("Servico não encontrado.");
        }
    }
}
