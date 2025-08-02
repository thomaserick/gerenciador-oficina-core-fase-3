package com.fiap.pj.core.usuario.domain.specification;

import com.fiap.pj.core.usuario.domain.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.fiap.pj.core.util.SpecificationUtils.likeTerm;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioSpecification {

    private static final String FIELD_NOME = "nome";
    private static final String FIELD_ATIVO = "ativo";

    public static Specification<Usuario> queContenhaNomeCom(String nome) {
        return (root, criteriaQuery, builder) ->
                builder.like(builder.upper(root.get(FIELD_NOME)), likeTerm(nome.trim().toUpperCase()));
    }

    public static Specification<Usuario> queContenhaAtivoIgualA(boolean ativo) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ATIVO), ativo);
    }
}
