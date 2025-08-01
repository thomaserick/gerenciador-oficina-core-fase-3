package com.fiap.pj.core.service.adapter.in.api.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "description", "price", "observation", "active"})
public interface ServiceResponse {

    String getId();

    String getDescription();

    String getPrice();

    String getObservation();

    boolean isActive();

}
