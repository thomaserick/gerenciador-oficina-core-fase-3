package com.fiap.pj.core.service.usecase.command;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateServiceCommand extends ServiceCommand {

    public CreateServiceCommand(String description, BigDecimal price, String observation) {
        super(description, price, observation);
    }
}
