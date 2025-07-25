package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.usecase.command.CriarUsuarioCommand;

public interface CriarUsuarioUseCase {

    Usuario handle(CriarUsuarioCommand cmd);
}
