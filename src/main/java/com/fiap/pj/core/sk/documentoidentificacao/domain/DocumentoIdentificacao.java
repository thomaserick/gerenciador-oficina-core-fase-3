package com.fiap.pj.core.sk.documentoidentificacao.domain;

import com.fiap.pj.core.sk.documentoidentificacao.domain.enums.OrigemDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class DocumentoIdentificacao implements Serializable {

    private static final long serialVersionUID = 4051790852438141983L;

    @Column(name = "documento_identificacao_origem")
    private OrigemDocumento origem;

    @Column(name = "documento_identificacao_numero")
    private String numero;

    public DocumentoIdentificacao(OrigemDocumento origem, String numero) {
        this.origem = origem;
        this.numero = numero;
    }

    public static DocumentoIdentificacao build(String numero) {
        return new DocumentoBrasileiro(numero);
    }
}