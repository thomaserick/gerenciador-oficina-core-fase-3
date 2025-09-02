package com.fiap.pj.infra.cliente.gateways;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.infra.cliente.persistence.ClienteEntity;

public class ClienteRepositoryMapper {

    ClienteEntity mapToTable(Cliente cliente) {
        return new ClienteEntity(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.isAtivo(),
                cliente.getEndereco(),
                cliente.getDocumentoIdentificacao(),
                cliente.getVeiculos()
        );
    }

    Cliente mapToDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.isAtivo(),
                entity.getEndereco(),
                entity.getDocumentoIdentificacao(), entity.getVeiculos());


    }
}
