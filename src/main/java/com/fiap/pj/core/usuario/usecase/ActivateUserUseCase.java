package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.ActivateUserCommand;

public interface ActivateUserUseCase {

    void handle(ActivateUserCommand cmd);
}
