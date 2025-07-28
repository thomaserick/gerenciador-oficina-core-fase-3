package com.fiap.pj.core.customer.usecase;

import com.fiap.pj.core.customer.usecase.command.ActivateCustomerCommand;

public interface ActivateCustomerUserCase {
    void handle(ActivateCustomerCommand cmd);
}
