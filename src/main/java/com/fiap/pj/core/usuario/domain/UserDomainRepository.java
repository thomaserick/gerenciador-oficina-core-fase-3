package com.fiap.pj.core.usuario.domain;


import com.fiap.pj.core.usuario.exception.UserExceptions.UserNotFoundException;
import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;

public interface UserDomainRepository extends BaseCrudRepository<User, UUID> {


    @Override
    default User findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }

}
