package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.InativarUsuarioCommand;

public interface InativarUsuarioUseCase {

    void handle(InativarUsuarioCommand cmd);
}
