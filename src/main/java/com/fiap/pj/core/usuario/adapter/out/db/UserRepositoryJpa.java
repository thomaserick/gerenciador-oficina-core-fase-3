package com.fiap.pj.core.usuario.adapter.out.db;


import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.domain.UserDomainRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface UserRepositoryJpa extends UserDomainRepository, Repository<User, UUID> {
}