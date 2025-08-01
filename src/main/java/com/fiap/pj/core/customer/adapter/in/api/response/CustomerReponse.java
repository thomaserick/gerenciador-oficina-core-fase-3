package com.fiap.pj.core.customer.adapter.in.api.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.sk.documentoidentificacao.domain.IdentificationDocument;
import com.fiap.pj.core.vehicle.domain.Vehicle;

import java.util.List;

@JsonPropertyOrder({"id", "name", "email", "phone", "address", "active"})
public interface CustomerReponse {

    String getId();

    String getName();

    String getEmail();

    String getPhone();

    String getAddress();

    IdentificationDocument getIdentificationDocument();

    @JsonIgnoreProperties({"customerId"})
    List<Vehicle> getVehicles();

    boolean isActive();
}
