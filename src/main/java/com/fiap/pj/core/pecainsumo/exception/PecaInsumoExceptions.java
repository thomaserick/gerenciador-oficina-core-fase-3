package com.fiap.pj.core.pecainsumo.exception;

public class PecaInsumoExceptions {
    private PecaInsumoExceptions() {
    }

    public static class PecaInsumoNaoEncontradoException extends RuntimeException {
        public PecaInsumoNaoEncontradoException() {
            super("Peça/Insumo não encontrado.");
        }
    }

    public static class PecaInsumoComRelacionamentoException extends RuntimeException {
        public PecaInsumoComRelacionamentoException() {
            super("Peça/Insumo com relacionamento, não permite excluir.");
        }
    }

    public static class QuantidadeEstoqueInsuficienteException extends RuntimeException {
        public QuantidadeEstoqueInsuficienteException() {
            super("Quantidade em estoque insuficiente.");
        }
    }
} 