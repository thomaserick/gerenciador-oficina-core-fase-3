package com.fiap.pj.core.user.domain.specification;

import com.fiap.pj.core.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.fiap.pj.core.util.SpecificationUtils.likeTerm;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSpecification {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_ACTIVE = "active";

    public static Specification<User> thatContainsNameWith(String name) {
        return (root, criteriaQuery, builder) ->
                builder.like(builder.upper(root.get(FIELD_NAME)), likeTerm(name.trim().toUpperCase()));
    }

    public static Specification<User> isActive(boolean active) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ACTIVE), active);
    }
}
