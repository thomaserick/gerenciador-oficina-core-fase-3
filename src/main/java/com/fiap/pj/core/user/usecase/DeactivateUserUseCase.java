package com.fiap.pj.core.user.usecase;

import com.fiap.pj.core.user.usecase.command.DeactivateUserCommand;

public interface DeactivateUserUseCase {

    void handle(DeactivateUserCommand cmd);
}
