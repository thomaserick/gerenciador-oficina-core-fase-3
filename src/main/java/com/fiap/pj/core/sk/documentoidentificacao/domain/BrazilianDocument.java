package com.fiap.pj.core.sk.documentoidentificacao.domain;

import com.fiap.pj.core.sk.documentoidentificacao.domain.enums.DocumentOrigin;

public class BrazilianDocument extends IdentificationDocument {

    private static final int CPF_LENGTH = 11;

    public BrazilianDocument(String number) {
        super(DocumentOrigin.BRASIL, number);
    }

    public static IdentificationDocument build(String number) {
        if (number.length() == CPF_LENGTH) {
            return new CPF(number);
        }
        return new CNPJ(number);
    }

}
