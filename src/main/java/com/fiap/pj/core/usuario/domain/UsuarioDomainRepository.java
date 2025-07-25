package com.fiap.pj.core.usuario.domain;


import com.fiap.pj.core.sk.jpa.BaseCrudRepository;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioNotFoundException;

import java.util.UUID;

public interface UsuarioDomainRepository extends BaseCrudRepository<Usuario, UUID> {


    @Override
    default Usuario findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(UsuarioNotFoundException::new);
    }

}
