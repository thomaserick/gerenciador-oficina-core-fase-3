package com.fiap.pj.core.service.adapter.out.db;


import com.fiap.pj.core.service.domain.ServiceDomainRepository;
import com.fiap.pj.core.vehicle.domain.Vehicle;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface ServiceRepositoryJpa extends ServiceDomainRepository, Repository<Vehicle, UUID> {

}