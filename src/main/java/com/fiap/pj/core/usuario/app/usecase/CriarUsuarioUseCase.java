package com.fiap.pj.core.usuario.app.usecase;

import com.fiap.pj.core.usuario.usecase.command.CriarUsuarioCommand;

import java.util.UUID;

public interface CriarUsuarioUseCase {

    UUID handle(CriarUsuarioCommand cmd);
}
