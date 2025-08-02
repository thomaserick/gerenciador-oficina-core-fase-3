package com.fiap.pj.core.veiculo.adapter.out.db;


import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.domain.VeiculoDomainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface VeiculoRepositoryJpa extends VeiculoDomainRepository, Repository<Veiculo, UUID> {


    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN TRUE ELSE FALSE END FROM Veiculo v WHERE v.placa = :placa")
    boolean existsByPlaca(@Param("placa") String placa);

}