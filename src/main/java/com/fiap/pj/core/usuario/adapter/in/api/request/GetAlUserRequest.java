package com.fiap.pj.core.usuario.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.usuario.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.core.util.SpecificationUtils.likeTerm;
import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public class GetAlUserRequest {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_ACTIVE = "active";

    private String name;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    private Boolean active;

    public Specification<User> buildSpecification() {
        Specification<User> specs = Specification.allOf();

        if (hasText(this.name)) {
            specs = specs.and(thatContainsNameWith());
        }

        if (Objects.nonNull(active)) {
            specs = specs.and(isActive());
        }
        return specs;
    }

    private Specification<User> thatContainsNameWith() {
        return (root, criteriaQuery, builder) ->
                builder.like(builder.upper(root.get(FIELD_NAME)), likeTerm(this.getName().trim().toUpperCase()));
    }

    private Specification<User> isActive() {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ACTIVE), this.getActive());
    }
}
