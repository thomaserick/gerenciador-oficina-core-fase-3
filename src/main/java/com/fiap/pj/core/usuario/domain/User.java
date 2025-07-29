package com.fiap.pj.core.usuario.domain;

import com.fiap.pj.core.usuario.domain.enums.Roles;
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
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {

    @ElementCollection
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private final Set<Roles> roles = new HashSet<>();

    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean active;

    public User(UUID id, String name, String lastName, String email, String password, boolean active) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.lastName = lastName;
        this.email = requireNonNull(email);
        this.password = requireNonNull(password);
        this.active = active;
    }

    public void addRoles(Collection<Roles> roles) {
        this.roles.addAll(roles);
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public void update(String name, String lastName, boolean active, String password, Set<Roles> roles) {

        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.password = password;
        updateRoles(roles);
    }

    public void updateRoles(Set<Roles> roles) {
        CollectionUtils.instanceNonNullCollection(this.roles, roles);
    }
}