package com.fiap.pj.core.usuario.adapter.out.db;


import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.domain.UserDomainRepository;
import com.fiap.pj.core.usuario.exception.UserExceptions.UserNotFoundException;
import com.fiap.pj.infra.jpa.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface UserRepositoryJpa extends UserDomainRepository, Repository<User, UUID>, ExtendedRepository<User, UUID>, JpaSpecificationExecutor<User> {

    @Override
    default User findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }
}