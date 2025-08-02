package com.fiap.pj.core.cliente.usecase;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.usecase.command.AlterarClienteCommand;

public interface AlterarClienteUserCase {

    Cliente handle(AlterarClienteCommand cmd);

}
