package com.fiap.pj.infra.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ExtendedRepository<T, I extends Serializable> extends JpaRepository<T, I> {

    List<T> findBy(Specification<T> spec, Pageable pageable);

    <S> Page<S> findProjectedBy(Specification<T> specs, Pageable pageable, Class<S> type);
}
