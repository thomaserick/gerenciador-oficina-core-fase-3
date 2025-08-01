package com.fiap.pj.core.user.usecase;

import com.fiap.pj.core.user.usecase.command.UpdateUserCommand;

public interface UpdateUserUseCase {

    void handle(UpdateUserCommand cmd);
}
