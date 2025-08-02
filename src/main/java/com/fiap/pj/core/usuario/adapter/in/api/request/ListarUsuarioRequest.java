package com.fiap.pj.core.usuario.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.core.usuario.domain.specification.UsuarioSpecification.queContenhaAtivoIgualA;
import static com.fiap.pj.core.usuario.domain.specification.UsuarioSpecification.queContenhaNomeCom;
import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public class ListarUsuarioRequest {

    private String nome;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    private Boolean ativo;

    public Specification<Usuario> buildSpecification() {
        Specification<Usuario> specs = Specification.allOf();

        if (hasText(this.nome)) {
            specs = specs.and(queContenhaNomeCom(this.nome));
        }

        if (Objects.nonNull(ativo)) {
            specs = specs.and(queContenhaAtivoIgualA(this.ativo));
        }
        return specs;
    }

}
