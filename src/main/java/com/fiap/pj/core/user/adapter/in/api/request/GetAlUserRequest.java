package com.fiap.pj.core.user.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.core.user.domain.specification.UserSpecification.isActive;
import static com.fiap.pj.core.user.domain.specification.UserSpecification.thatContainsNameWith;
import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public class GetAlUserRequest {

    private String name;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    private Boolean active;

    public Specification<User> buildSpecification() {
        Specification<User> specs = Specification.allOf();

        if (hasText(this.name)) {
            specs = specs.and(thatContainsNameWith(this.name));
        }

        if (Objects.nonNull(active)) {
            specs = specs.and(isActive(this.active));
        }
        return specs;
    }

}
