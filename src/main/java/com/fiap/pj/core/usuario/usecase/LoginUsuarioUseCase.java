package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.infra.usuario.controller.response.LoginUsuarioResponse;
import com.fiap.pj.core.usuario.usecase.command.LoginUsuarioCommand;

public interface LoginUsuarioUseCase {

    LoginUsuarioResponse handle(LoginUsuarioCommand cmd);
}
