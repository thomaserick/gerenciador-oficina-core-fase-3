package com.fiap.pj.core.user.domain;


import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;


public interface UserDomainRepository extends BaseCrudRepository<User, UUID> {

    @Override
    User findByIdOrThrowNotFound(UUID id);


}
