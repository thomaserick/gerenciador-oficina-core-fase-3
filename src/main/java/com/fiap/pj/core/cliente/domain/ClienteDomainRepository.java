package com.fiap.pj.core.cliente.domain;


import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;

public interface ClienteDomainRepository extends BaseCrudRepository<Cliente, UUID> {

    boolean existsByIDocumentoIdentificacaoNumero(String numero);

}
