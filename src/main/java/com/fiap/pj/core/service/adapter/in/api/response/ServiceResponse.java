package com.fiap.pj.core.service.adapter.in.api.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"id", "description", "price", "observation", "active"})
public interface ServiceResponse {

    String getId();

    String getDescription();

    BigDecimal getPrice();

    String getObservation();

    boolean isActive();

}
