package com.fiap.pj.core.usuario.adapter.out.db;


import com.fiap.pj.core.usuario.domain.UserDomainRepository;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UserNaoEncontradoException;
import com.fiap.pj.infra.jpa.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface UsuarioRepositoryJpa extends UserDomainRepository, Repository<Usuario, UUID>, ExtendedRepository<Usuario, UUID>, JpaSpecificationExecutor<Usuario> {

    @Override
    default Usuario findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(UserNaoEncontradoException::new);
    }
}