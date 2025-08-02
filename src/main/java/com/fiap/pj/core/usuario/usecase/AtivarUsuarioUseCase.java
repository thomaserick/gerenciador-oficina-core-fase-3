package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.AtivarUsuarioCommand;

public interface AtivarUsuarioUseCase {

    void handle(AtivarUsuarioCommand cmd);
}
