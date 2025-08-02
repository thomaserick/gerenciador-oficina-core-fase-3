package com.fiap.pj.core.sk.documentoidentificacao.domain;

import com.fiap.pj.core.sk.documentoidentificacao.domain.enums.OrigemDocumento;

public class DocumentoBrasileiro extends DocumentoIdentificacao {
    public DocumentoBrasileiro(String number) {
        super(OrigemDocumento.BRASIL, number);
    }
}
