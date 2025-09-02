package com.fiap.pj.infra.cliente.gateways;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.infra.cliente.persistence.ClienteRepositoryJpa;

import java.util.Optional;
import java.util.UUID;

public class ClienteRepositoryGatewayImpl implements ClienteGateway {

    private final ClienteRepositoryJpa repository;
    private final ClienteRepositoryMapper clienteRepositoryMapper;

    public ClienteRepositoryGatewayImpl(ClienteRepositoryJpa repository, ClienteRepositoryMapper clienteRepositoryMapper) {
        this.repository = repository;
        this.clienteRepositoryMapper = clienteRepositoryMapper;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        var clienteEntity = clienteRepositoryMapper.mapToTable(cliente);
        return clienteRepositoryMapper.mapToDomain(repository.save(clienteEntity));
    }

    @Override
    public void alterar(Cliente cliente) {
        var clienteEntity = clienteRepositoryMapper.mapToTable(cliente);
        repository.save(clienteEntity);
    }

    @Override
    public void excluir(Cliente cliente) {
        var clienteEntity = clienteRepositoryMapper.mapToTable(cliente);
        repository.delete(clienteEntity);
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(clienteRepositoryMapper::mapToDomain);

    }

    @Override
    public boolean existsByDocumentoIdentificacaoNumero(String documento) {
        return repository.existsByDocumentoIdentificacaoNumero(documento);
    }

}
