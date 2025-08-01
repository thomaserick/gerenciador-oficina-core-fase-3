package com.fiap.pj.core.user.usecase;

import com.fiap.pj.core.user.domain.User;
import com.fiap.pj.core.user.usecase.command.CreateUserCommand;

public interface CreateUserUseCase {

    User handle(CreateUserCommand cmd);
}
