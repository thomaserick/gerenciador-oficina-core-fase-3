package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.AlterarUsuarioCommand;

public interface AlterarUsuarioUseCase {

    void handle(AlterarUsuarioCommand cmd);
}
