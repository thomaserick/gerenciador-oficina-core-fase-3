package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.DeactivateUserCommand;

public interface DeactivateUserUseCase {

    void handle(DeactivateUserCommand cmd);
}
