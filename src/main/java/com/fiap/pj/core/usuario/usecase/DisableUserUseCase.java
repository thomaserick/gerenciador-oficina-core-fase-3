package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.DisableUserCommand;

public interface DisableUserUseCase {

    void handle(DisableUserCommand cmd);
}
