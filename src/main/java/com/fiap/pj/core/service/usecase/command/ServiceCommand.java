package com.fiap.pj.core.service.usecase.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class ServiceCommand {

    @NotBlank(message = "Descricao do servico não pode estar vazio.")
    protected String description;
    protected BigDecimal price;
    protected String observation;

    protected ServiceCommand(String description, BigDecimal price, String observation) {
        this.description = description;
        this.price = price;
        this.observation = observation;
    }
}
