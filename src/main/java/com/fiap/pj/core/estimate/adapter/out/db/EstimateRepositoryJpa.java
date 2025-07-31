package com.fiap.pj.core.estimate.adapter.out.db;


import com.fiap.pj.core.estimate.domain.Estimate;
import com.fiap.pj.core.estimate.domain.EstimateDomainRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface EstimateRepositoryJpa extends EstimateDomainRepository, Repository<Estimate, UUID> {
}