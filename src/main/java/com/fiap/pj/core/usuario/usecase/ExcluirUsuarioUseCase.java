package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.usecase.command.ExcluirUsuarioCommand;

public interface ExcluirUsuarioUseCase {

    void handle(ExcluirUsuarioCommand cmd);
}
