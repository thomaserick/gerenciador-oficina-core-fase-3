package com.fiap.pj.core.usuario.domain;

import com.fiap.pj.core.usuario.domain.enums.Perfil;
import com.fiap.pj.core.util.CollectionUtils;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@Getter
public class Usuario {

    @ElementCollection
    @CollectionTable(name = "usuarios_perfil", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "perfil")
    private final Set<Perfil> perfis = new HashSet<>();

    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sobreNome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private boolean ativo;

    public Usuario(UUID id, String nome, String sobreNome, String email, String senha, boolean ativo) {
        this.id = requireNonNull(id);
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.email = requireNonNull(email);
        this.senha = requireNonNull(senha);
        this.ativo = ativo;
    }

    public void adicionarPerfils(Collection<Perfil> perfis) {
        this.perfis.addAll(perfis);
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void alterar(String nome, String sobreNome, boolean ativo, String senha, Set<Perfil> perfis) {

        this.nome = nome;
        this.sobreNome = sobreNome;
        this.ativo = ativo;
        this.senha = senha;
        alterarPerfis(perfis);
    }

    public void alterarPerfis(Set<Perfil> perfis) {
        CollectionUtils.instanceNonNullCollection(this.perfis, perfis);
    }
}