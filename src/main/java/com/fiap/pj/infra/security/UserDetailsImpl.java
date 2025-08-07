package com.fiap.pj.infra.security;

import com.fiap.pj.core.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private transient Usuario usuario;
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return this.usuario.getEmail();
    }

    public String getId() {
        return this.usuario.getId().toString();
    }

    public String getNomeCompleto() {
        return usuario.getNomeCompleto();
    }
}
