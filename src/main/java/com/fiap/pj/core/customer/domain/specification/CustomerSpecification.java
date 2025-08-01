package com.fiap.pj.core.customer.domain.specification;

import com.fiap.pj.core.customer.domain.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.fiap.pj.core.util.SpecificationUtils.likeTerm;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerSpecification {

    private static final String TABLE_VEHICLE = "vehicles";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_ACTIVE = "active";
    private static final String FIELD_DOCUMENT_IDENTIFICATION = "identificationDocument";
    private static final String FIELD_DOCUMENT_IDENTIFICATION_NUMBER = "number";
    private static final String FIELD_PLATE = "plate";

    public static Specification<Customer> thatContainsNameWith(String name) {
        return (root, criteriaQuery, builder) ->
                builder.like(builder.upper(root.get(FIELD_NAME)), likeTerm(name.trim().toUpperCase()));
    }

    public static Specification<Customer> isActive(boolean active) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_ACTIVE), active);
    }

    public static Specification<Customer> thatContainsIdentificationDocumentWith(String identificationDocument) {
        return (root, criteriaQuery, builder) ->
                builder.equal(root.get(FIELD_DOCUMENT_IDENTIFICATION).get(FIELD_DOCUMENT_IDENTIFICATION_NUMBER), identificationDocument.trim().toUpperCase());
    }

    public static Specification<Customer> thatContainsPlateWith(String plate) {
        return (root, criteriaQuery, builder) ->
        {
            var vehicle = root.join(TABLE_VEHICLE);
            return builder.like(vehicle.get(FIELD_PLATE), likeTerm(plate.trim().toUpperCase()));
        };
    }
}
