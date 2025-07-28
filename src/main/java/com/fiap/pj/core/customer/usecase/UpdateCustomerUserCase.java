package com.fiap.pj.core.customer.usecase;

import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.usecase.command.UpdateCustomerCommand;

public interface UpdateCustomerUserCase {

    Customer handle(UpdateCustomerCommand cmd);

}
