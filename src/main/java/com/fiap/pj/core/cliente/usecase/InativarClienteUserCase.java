package com.fiap.pj.core.cliente.usecase;

import com.fiap.pj.core.cliente.usecase.command.InativarClienteCommand;

public interface InativarClienteUserCase {

    void handle(InativarClienteCommand cmd);

}
