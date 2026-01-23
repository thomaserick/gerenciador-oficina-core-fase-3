package com.fiap.pj.core.cliente.app.usecase;

import com.fiap.pj.core.cliente.app.usecase.command.ExistsClienteByCpfAndAtivoCommand;

public interface ExistsClienteByCpfUseCase {

    boolean handle(ExistsClienteByCpfAndAtivoCommand cmd);

}
