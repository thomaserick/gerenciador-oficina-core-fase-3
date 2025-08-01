package com.fiap.pj.core.service.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.service.domain.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.core.service.domain.specification.ServiceSpecification.isActive;
import static com.fiap.pj.core.service.domain.specification.ServiceSpecification.thatContainsNameWith;
import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public class GetAllServiceRequest {


    private String name;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    private Boolean active;

    public Specification<ServiceEntity> buildSpecification() {
        Specification<ServiceEntity> specs = Specification.allOf();

        if (hasText(this.name)) {
            specs = specs.and(thatContainsNameWith(this.name));
        }

        if (Objects.nonNull(active)) {
            specs = specs.and(isActive(this.active));
        }
        return specs;
    }

}
