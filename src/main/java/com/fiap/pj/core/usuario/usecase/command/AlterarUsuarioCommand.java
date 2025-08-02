package com.fiap.pj.core.usuario.usecase.command;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.usuario.domain.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;


@Getter
public class AlterarUsuarioCommand extends UserCommand {

    @Setter
    @JsonIgnore
    private UUID id;

    public AlterarUsuarioCommand(UUID id, String name, String lastName, String email, String password, boolean active, Set<Perfil> roles) {
        super(name, lastName, email, password, active, roles);
        this.id = id;
    }
}




