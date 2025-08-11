package com.fiap.pj.core.ordemservico.exception;

public class OrdemServicoExceptions {
    private OrdemServicoExceptions() {
    }

    public static class OrdemServicoNaoEncontradaException extends RuntimeException {
        public OrdemServicoNaoEncontradaException() {
            super("Ordem serviço não encontrado.");
        }
    }

    public static class OrdemServicoStatusInvalidoDiagnosticoException extends RuntimeException {
        public OrdemServicoStatusInvalidoDiagnosticoException() {
            super("Ordem serviço não pode receber um diagnóstico no status atual.");
        }
    }

    public static class OrdemServicoStatusInvalidoAguardandoAprovacaoException extends RuntimeException {
        public OrdemServicoStatusInvalidoAguardandoAprovacaoException() {
            super("Ordem serviço não pode ser movida para aguardando aprovação, pois não passou por um diagnóstico.");
        }
    }

    public static class OrdemServicoStatusInvalidoEmExecucaoException extends RuntimeException {
        public OrdemServicoStatusInvalidoEmExecucaoException() {
            super("Ordem serviço não pode ser movida para em execução, pois não passou por um uma aprovação ou diagnóstico.");
        }
    }

    public static class OrdemServicoStatusInvalidoFinalizadaException extends RuntimeException {
        public OrdemServicoStatusInvalidoFinalizadaException() {
            super("Ordem serviço não pode ser movida para em finalizada, pois nunca esteve em execução.");
        }
    }

    public static class OrdemServicoStatusInvalidoAguardandoRetiradaException extends RuntimeException {
        public OrdemServicoStatusInvalidoAguardandoRetiradaException() {
            super("Ordem serviço não pode ser movida para aguardando retirada, pois não esta finalizada.");
        }
    }

    public static class OrdemServicoStatusInvalidoEntregueException extends RuntimeException {
        public OrdemServicoStatusInvalidoEntregueException() {
            super("Ordem serviço não pode ser movida para entregue, pois não esta aguardando retirada.");
        }
    }
}