package com.fiap.pj.infra.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface BaseCrudRepository<D, K> {
    Optional<D> findById(K id);

    D save(D domain);

    void delete(D domain);

    void deleteAll(Iterable<? extends D> entities);

    <S> Page<S> findProjectedBy(Specification<D> specs, Pageable pageable, Class<S> type);


    default D findByIdOrThrowNotFound(K id) {
        return findById(id).orElseThrow(RuntimeException::new);
    }

}