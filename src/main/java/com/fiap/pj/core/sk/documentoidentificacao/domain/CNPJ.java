package com.fiap.pj.core.sk.documentoidentificacao.domain;

public class CNPJ extends BrazilianDocument {

    @org.hibernate.validator.constraints.br.CNPJ
    private final String number;

    protected CNPJ(String number) {
        super(number);
        this.number = number;
    }

}
