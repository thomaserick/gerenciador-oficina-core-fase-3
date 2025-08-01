package com.fiap.pj.core.user.usecase;

import com.fiap.pj.core.user.usecase.command.ActivateUserCommand;

public interface ActivateUserUseCase {

    void handle(ActivateUserCommand cmd);
}
