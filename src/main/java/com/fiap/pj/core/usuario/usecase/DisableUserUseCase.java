package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.DisablesUserCommand;

public interface DisableUserUseCase {

    void handle(DisablesUserCommand cmd);
}
