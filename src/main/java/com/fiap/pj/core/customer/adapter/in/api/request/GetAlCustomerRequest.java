package com.fiap.pj.core.customer.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.fiap.pj.core.customer.domain.specification.CustomerSpecification.isActive;
import static com.fiap.pj.core.customer.domain.specification.CustomerSpecification.thatContainsIdentificationDocumentWith;
import static com.fiap.pj.core.customer.domain.specification.CustomerSpecification.thatContainsNameWith;
import static com.fiap.pj.core.customer.domain.specification.CustomerSpecification.thatContainsPlateWith;
import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public class GetAlCustomerRequest {

    private String name;
    private String identificationDocument;
    private String plate;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    private Boolean active;

    public Specification<Customer> buildSpecification() {
        Specification<Customer> specs = Specification.allOf();

        if (hasText(this.name)) {
            specs = specs.and(thatContainsNameWith(this.name));
        }

        if (hasText(this.identificationDocument)) {
            specs = specs.and(thatContainsIdentificationDocumentWith(this.identificationDocument));
        }

        if (hasText(this.plate)) {
            specs = specs.and(thatContainsPlateWith(this.plate));
        }

        if (Objects.nonNull(active)) {
            specs = specs.and(isActive(this.active));
        }
        return specs;
    }

}
