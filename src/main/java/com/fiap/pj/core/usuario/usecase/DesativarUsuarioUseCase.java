package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.DesativarUsuarioCommand;

public interface DesativarUsuarioUseCase {

    void handle(DesativarUsuarioCommand cmd);
}
