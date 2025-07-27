package com.fiap.pj.core.sk.documentoidentificacao.domain;

import com.fiap.pj.core.sk.documentoidentificacao.domain.enums.DocumentOrigin;

public class BrazilianDocument extends IdentificationDocument {
    public BrazilianDocument(String number) {
        super(DocumentOrigin.BRASIL, number);
    }
}
