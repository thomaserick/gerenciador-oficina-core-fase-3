package com.fiap.pj.core.customer.usecase;

import com.fiap.pj.core.customer.usecase.command.DeactivateCustomerCommand;

public interface DeactivateCustomerUserCase {

    void handle(DeactivateCustomerCommand cmd);

}
