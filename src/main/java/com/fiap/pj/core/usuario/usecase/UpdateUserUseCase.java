package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.UpdateUserCommand;

public interface UpdateUserUseCase {

    void handle(UpdateUserCommand cmd);
}
