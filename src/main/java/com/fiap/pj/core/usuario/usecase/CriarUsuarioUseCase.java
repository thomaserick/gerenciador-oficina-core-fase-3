package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.usecase.command.UsuarioCommand;

public interface CriarUsuarioUseCase {

    Usuario handle(UsuarioCommand cmd);
}
