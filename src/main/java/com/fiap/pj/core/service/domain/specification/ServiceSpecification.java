package com.fiap.pj.core.service.domain.specification;

import com.fiap.pj.core.service.domain.ServiceEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.fiap.pj.core.util.SpecificationUtils.likeTerm;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceSpecification {

    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_ACTIVE = "active";

    public static Specification<ServiceEntity> thatContainsNameWith(String name) {
        return (root, criteriaQuery, builder) ->
                builder.like(builder.upper(root.get(FIELD_DESCRIPTION)), likeTerm(name.trim().toUpperCase()));
    }

    public static Specification<ServiceEntity> isActive(boolean active) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ACTIVE), active);
    }
}
