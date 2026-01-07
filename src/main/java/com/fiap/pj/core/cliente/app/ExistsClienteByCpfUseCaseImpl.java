package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.ExistsClienteByCpfUseCase;
import com.fiap.pj.core.cliente.app.usecase.command.ExistsClienteByCpfAndAtivoCommand;


public class ExistsClienteByCpfUseCaseImpl implements ExistsClienteByCpfUseCase {

    private final ClienteGateway clienteGateway;

    public ExistsClienteByCpfUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public boolean handle(ExistsClienteByCpfAndAtivoCommand cmd) {
        return clienteGateway.existsByDocumentoIdentificacaoNumeroAndAtivo(cmd.cpf());
    }
}
