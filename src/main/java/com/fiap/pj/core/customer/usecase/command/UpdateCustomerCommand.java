package com.fiap.pj.core.customer.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class UpdateCustomerCommand extends CustomerCommand {

    @Setter
    @JsonIgnore
    private UUID id;

    public UpdateCustomerCommand(UUID id, String name, String email, String phone, String address) {
        super(name, email, phone, address);
        this.id = id;
    }
}
