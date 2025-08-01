package com.fiap.pj.core.usuario.adapter.in.api.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "lastName", "email", "active"})
public interface UserReponse {

    String getId();

    String getName();

    String getLastName();

    String getEmail();

    boolean isActive();
}
