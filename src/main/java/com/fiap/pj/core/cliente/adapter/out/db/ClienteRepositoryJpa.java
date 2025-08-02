package com.fiap.pj.core.cliente.adapter.out.db;


import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface ClienteRepositoryJpa extends ClienteDomainRepository, Repository<Cliente, UUID> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Cliente c WHERE c.documentoIdentificacao.numero = :numero")
    boolean existsByIDocumentoIdentificacaoNumero(@Param("numero") String numero);

    @Override
    default Cliente findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(ClienteNaoEncontradoException::new);
    }
}