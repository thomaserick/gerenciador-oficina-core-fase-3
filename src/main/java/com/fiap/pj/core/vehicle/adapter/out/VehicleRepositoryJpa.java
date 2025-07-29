package com.fiap.pj.core.vehicle.adapter.out;


import com.fiap.pj.core.vehicle.domain.Vehicle;
import com.fiap.pj.core.vehicle.domain.VehicleDomainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface VehicleRepositoryJpa extends VehicleDomainRepository, Repository<Vehicle, UUID> {


    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN TRUE ELSE FALSE END FROM Vehicle v WHERE v.plate = :plate")
    boolean existsByPlate(@Param("plate") String plate);

}