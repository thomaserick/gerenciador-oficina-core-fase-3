package com.fiap.pj.core.cliente.usecase;

import com.fiap.pj.core.cliente.usecase.command.ExcluirClienteCommand;

public interface ExcluirClienteUserCase {
    void handle(ExcluirClienteCommand cmd);
}
