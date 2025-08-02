package com.fiap.pj.core.cliente.usecase;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.usecase.command.CriarClienteCommand;

public interface CriarClienteUserCase {

    Cliente handle(CriarClienteCommand cmd);

}
