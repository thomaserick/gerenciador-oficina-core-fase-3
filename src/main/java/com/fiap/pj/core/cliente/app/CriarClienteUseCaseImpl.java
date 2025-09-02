package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.CriarClienteUserCase;
import com.fiap.pj.core.cliente.app.usecase.command.CriarClienteCommand;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.DocumentoIdentificacaoDuplicadoException;
import com.fiap.pj.core.sk.documentoidentificacao.domain.DocumentoIdentificacao;

import java.util.UUID;


public class CriarClienteUseCaseImpl implements CriarClienteUserCase {

    private ClienteGateway clienteGateway;

    public CriarClienteUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente handle(CriarClienteCommand cmd) {

        if (clienteGateway.existsByDocumentoIdentificacaoNumero(cmd.getDocumentoIdentificacao())) {
            throw new DocumentoIdentificacaoDuplicadoException();
        }

        var documentoIdentificacao = DocumentoIdentificacao.build(cmd.getDocumentoIdentificacao());

        var cliente = Cliente.builder()
                .id(UUID.randomUUID())
                .nome(cmd.getNome())
                .email(cmd.getEmail())
                .ativo(true)
                .telefone(cmd.getTelefone())
                .endereco(cmd.getEndereco())
                .documentoIdentificacao(documentoIdentificacao)
                .build();

        return clienteGateway.salvar(cliente);
    }
}
