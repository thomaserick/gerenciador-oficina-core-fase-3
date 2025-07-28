package com.fiap.pj.core.customer.domain;


import com.fiap.pj.core.sk.documentoidentificacao.domain.IdentificationDocument;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@Getter
public class Customer {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private boolean active;
    private String address;

    @Embedded
    private IdentificationDocument identificationDocument;

    @Builder
    public Customer(UUID id, String name, String email, String phone, boolean active, String address, IdentificationDocument identificationDocument) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.address = requireNonNull(address);
        this.identificationDocument = requireNonNull(identificationDocument);
    }

    public void activate() {
        this.active = true;
    }

    public void disable() {
        this.active = false;
    }

    public void update(String name, String email, String address, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}
