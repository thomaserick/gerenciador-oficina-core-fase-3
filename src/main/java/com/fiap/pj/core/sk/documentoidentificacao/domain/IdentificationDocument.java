package com.fiap.pj.core.sk.documentoidentificacao.domain;

import com.fiap.pj.core.sk.documentoidentificacao.domain.enums.DocumentOrigin;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class IdentificationDocument implements Serializable {

    private static final long serialVersionUID = 4051790852438141983L;

    @Column(name = "identification_document_origin")
    private DocumentOrigin origin;

    @Column(name = "identification_document_number")
    private String number;

    public IdentificationDocument(DocumentOrigin origin, String number) {
        this.origin = origin;
        this.number = number;
    }

    public static IdentificationDocument build(String number) {
        return new BrazilianDocument(number);
    }
}