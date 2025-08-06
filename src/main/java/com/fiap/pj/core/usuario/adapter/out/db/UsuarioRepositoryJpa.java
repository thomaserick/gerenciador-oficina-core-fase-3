package com.fiap.pj.core.usuario.adapter.out.db;


import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.domain.UsuarioDomainRepository;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioNaoEncontradoException;
import com.fiap.pj.infra.jpa.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepositoryJpa extends UsuarioDomainRepository, Repository<Usuario, UUID>, ExtendedRepository<Usuario, UUID>, JpaSpecificationExecutor<Usuario> {


    Optional<Usuario> findByEmail(String email);

    @Override
    default Usuario findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(UsuarioNaoEncontradoException::new);
    }
}