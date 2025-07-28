package com.fiap.pj.core.customer.usecase;

import com.fiap.pj.core.customer.usecase.command.DisableCustomerCommand;

public interface DisableCustomerUserCase {

    void handle(DisableCustomerCommand cmd);

}
