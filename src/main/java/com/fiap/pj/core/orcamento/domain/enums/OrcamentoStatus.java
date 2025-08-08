package com.fiap.pj.core.orcamento.domain.enums;

public enum OrcamentoStatus {

    AGUARDANDO_APROVACAO, APROVADO, REPROVADO;


    public boolean isAguardandoAprovacao() {
        return this.equals(AGUARDANDO_APROVACAO);
    }

    public boolean isReprovado() {
        return this.equals(REPROVADO);
    }

    public boolean isAprovado() {
        return this.equals(APROVADO);
    }
}
