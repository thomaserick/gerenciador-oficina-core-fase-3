package com.fiap.pj.core.vehicle.domain;


import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;

public interface VehicleDomainRepository extends BaseCrudRepository<Vehicle, UUID> {

    boolean existsByPlate(String plate);
}
