package com.fiap.pj.core.cliente.usecase;

import com.fiap.pj.core.cliente.usecase.command.AtivarClienteCommand;

public interface AtivarClienteUserCase {
    void handle(AtivarClienteCommand cmd);
}
