package com.fiap.pj.core.cliente.app.gateways;

import com.fiap.pj.core.cliente.domain.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteGateway {

    Cliente salvar(Cliente cliente);


    void alterar(Cliente cliente);

    void excluir(Cliente cliente);

    Optional<Cliente> buscarPorId(UUID id);

    boolean existsByDocumentoIdentificacaoNumero(String documento);
}
