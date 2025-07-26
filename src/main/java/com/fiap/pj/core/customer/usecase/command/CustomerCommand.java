package com.fiap.pj.core.customer.usecase.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public abstract class CustomerCommand {

    @NotBlank(message = "Nome do cliente não pode estar vazio.")
    protected String name;
    protected String email;
    protected String phone;
    protected boolean active;

    @NotBlank(message = "Endereco do cliente não pode estar vazio.")
    protected String address;

    protected CustomerCommand(String name, String email, String phone, boolean active, String address) {
        this.name = requireNonNull(name);
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.address = requireNonNull(address);
    }
}
