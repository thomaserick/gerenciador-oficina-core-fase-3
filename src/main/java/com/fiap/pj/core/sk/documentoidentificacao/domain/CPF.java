package com.fiap.pj.core.sk.documentoidentificacao.domain;

public class CPF extends BrazilianDocument {

    @org.hibernate.validator.constraints.br.CPF
    private final String number;

    protected CPF(String number) {
        super(number);
        this.number = number;
    }
}
