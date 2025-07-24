package com.fiap.pj.core.usuario.adapter.out;


import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.domain.UsuarioDomainRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface UsuarioRepositoryJpa extends UsuarioDomainRepository, Repository<Usuario, UUID> {
}