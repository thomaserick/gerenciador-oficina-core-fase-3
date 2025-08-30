package com.fiap.pj.infra.usuario.gateways;

import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.infra.usuario.persistence.UsuarioRepositoryJpa;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsuarioRepositoryGatewayImpl implements UsuarioGateway {

    private final UsuarioRepositoryJpa repository;
    private final UsuarioRepositoryMapper usuarioRepositoryMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioRepositoryGatewayImpl(UsuarioRepositoryJpa repository, UsuarioRepositoryMapper usuarioRepositoryMapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.usuarioRepositoryMapper = usuarioRepositoryMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario criarUsuario(Usuario usuario) {
        var usuarioEntity = usuarioRepositoryMapper.mapToTable(usuario, passwordEncoder);
        return usuarioRepositoryMapper.mapToDomain(repository.save(usuarioEntity));
    }
}
