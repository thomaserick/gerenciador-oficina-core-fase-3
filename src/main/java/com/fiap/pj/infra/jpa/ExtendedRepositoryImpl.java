package com.fiap.pj.infra.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.io.Serializable;
import java.util.List;

public class ExtendedRepositoryImpl<T, I extends Serializable> extends SimpleJpaRepository<T, I>
        implements ExtendedRepository<T, I> {
    private static final Long COUNT_MIN = 0L;
    private static final Long COUNT_MAX = Long.MAX_VALUE;
    private static final int JUMP = 1;

    private ProjectionFactory factory;

    public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.factory = new SpelAwareProxyProjectionFactory();
    }

    @Override
    public List<T> findBy(Specification<T> spec, Pageable pageable) {
        TypedQuery<T> query = getQuery(spec, pageable);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize() + JUMP);
        return query.getResultList();
    }

    @Override
    public <S> Page<S> findProjectedBy(Specification<T> specs, Pageable pageable, Class<S> type) {
        List<T> result = this.findBy(specs, pageable);
        boolean hasNext = result.size() == (pageable.getPageSize() + JUMP);

        if (hasNext) {
            result.remove(result.size() - JUMP);
        }

        List<S> list = result.stream().map(entity -> factory.createProjection(type, entity)).toList();
        return new PageImpl<>(list, pageable, hasNext ? COUNT_MAX : COUNT_MIN);
    }
}
