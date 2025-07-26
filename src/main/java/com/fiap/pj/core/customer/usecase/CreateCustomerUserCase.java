package com.fiap.pj.core.customer.usecase;

import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.usecase.command.CreateCustomerCommand;

public interface CreateCustomerUserCase {

    Customer handle(CreateCustomerCommand cmd);

}
