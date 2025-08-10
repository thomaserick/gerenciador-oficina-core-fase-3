package com.fiap.pj.core.ordemservico.exception;

public class OrdemServicoExceptions {
    private OrdemServicoExceptions() {
    }

    public static class OrdemServicoEncontradoException extends RuntimeException {
        public OrdemServicoEncontradoException() {
            super("Ordem serviço não encontrado.");
        }
    }

    public static class OrdemServicoStatusInvalidoDianosticoException extends RuntimeException {
        public OrdemServicoStatusInvalidoDianosticoException() {
            super("Ordem serviço não pode receber um diagnóstico no status atual.");
        }
    }
}