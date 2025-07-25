package com.fiap.pj.core.sk.jpa;

import java.util.Optional;

public interface BaseCrudRepository<D, K> {
    Optional<D> findById(K id);

    D save(D domain);

    void delete(D domain);

    void deleteAll(Iterable<? extends D> entities);

    default D findByIdOrThrowNotFound(K id) {
        return findById(id).orElseThrow(RuntimeException::new);
    }

}