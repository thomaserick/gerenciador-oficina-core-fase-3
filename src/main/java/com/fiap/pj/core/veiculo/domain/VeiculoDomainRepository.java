package com.fiap.pj.core.veiculo.domain;


import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;

public interface VeiculoDomainRepository extends BaseCrudRepository<Veiculo, UUID> {

    boolean existsByPlaca(String placa);
}
