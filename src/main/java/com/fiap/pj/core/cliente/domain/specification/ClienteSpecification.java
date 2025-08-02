package com.fiap.pj.core.cliente.domain.specification;

import com.fiap.pj.core.cliente.domain.Cliente;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.fiap.pj.core.util.SpecificationUtils.likeTerm;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteSpecification {

    private static final String TABLE_VEICULO = "veiculos";
    private static final String FIELD_NOME = "nome";
    private static final String FIELD_ATIVO = "ativo";
    private static final String FIELD_DOCUMENTO_IDENTIFICACAO = "documentoIdentificacao";
    private static final String FIELD_DOCUMENTO_IDENTIFICACAO_NUMERO = "numero";
    private static final String FIELD_PLACA = "placa";

    public static Specification<Cliente> queContenhaNomeCom(String name) {
        return (root, criteriaQuery, builder) ->
                builder.like(builder.upper(root.get(FIELD_NOME)), likeTerm(name.trim().toUpperCase()));
    }

    public static Specification<Cliente> queContenhaAtivoIgualA(boolean active) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ATIVO), active);
    }

    public static Specification<Cliente> queContenhaDocumentoIdentificacaoCom(String identificationDocument) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_DOCUMENTO_IDENTIFICACAO).get(FIELD_DOCUMENTO_IDENTIFICACAO_NUMERO), identificationDocument.trim().toUpperCase());
    }

    public static Specification<Cliente> queContenhaPlacaIgualA(String plate) {
        return (root, criteriaQuery, builder) ->
        {
            var veiculo = root.join(TABLE_VEICULO);
            return builder.like(veiculo.get(FIELD_PLACA), likeTerm(plate.trim().toUpperCase()));
        };
    }
}
