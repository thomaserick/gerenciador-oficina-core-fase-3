package com.fiap.pj.core.usuario.domain;


import com.fiap.pj.core.sk.jpa.BaseCrudRepository;
import com.fiap.pj.core.usuario.exception.UserExceptions.UsuarioNotFoundException;

import java.util.UUID;

public interface UserDomainRepository extends BaseCrudRepository<User, UUID> {


    @Override
    default User findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(UsuarioNotFoundException::new);
    }

}
