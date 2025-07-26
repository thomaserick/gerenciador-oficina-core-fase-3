package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.usecase.command.CreateUserCommand;

public interface CreateUserUseCase {

    User handle(CreateUserCommand cmd);
}
