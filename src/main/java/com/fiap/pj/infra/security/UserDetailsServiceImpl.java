package com.fiap.pj.infra.security;

import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.domain.UsuarioDomainRepository;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioNaoEncontradoException;
import com.fiap.pj.core.util.security.SecurityContextUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioDomainRepository repository;
    private final UsuarioDomainRepository usuarioDomainRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsuarioNaoEncontradoException {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("O Usuário não foi encontrado com o e-mail: " + email));

        var perfis = usuario.getPerfis().stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.name())).toList();

        return new UserDetailsImpl(usuario, perfis);
    }

    public Usuario loadUsuarioFromSecurityContext() throws UsuarioNaoEncontradoException {
        String email = SecurityContextUtils.getLogin();

        return this.usuarioDomainRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("O Usuário não foi encontrado com o e-mail: " + email));
    }
}