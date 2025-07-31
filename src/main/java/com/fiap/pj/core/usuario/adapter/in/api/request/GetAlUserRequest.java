package com.fiap.pj.core.usuario.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.usuario.domain.User;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.util.StringUtils.hasText;

@Data
public class GetAlUserRequest {
    private String name;

    @Setter
    @JsonIgnore
    private Pageable pageable;

    public Specification<User> buildSpecification() {
        Specification<User> specs = Specification.allOf();

        if (hasText(this.name)) {
            specs = specs.and(thatContainsNameWith(this.name));
        }

        specs = specs.and(isActive());
        return specs;
    }

    private Specification<User> thatContainsNameWith(String name) {
        return (root, criteriaQuery, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

    private Specification<User> isActive() {
        return (root, criteriaQuery, builder) -> builder.isTrue(root.get("active"));
    }
}
