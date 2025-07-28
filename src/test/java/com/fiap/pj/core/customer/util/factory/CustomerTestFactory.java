package com.fiap.pj.core.customer.util.factory;


import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.usecase.command.CreateCustomerCommand;
import com.fiap.pj.core.customer.usecase.command.UpdateCustomerCommand;
import com.fiap.pj.core.sk.documentoidentificacao.domain.IdentificationDocument;
import com.fiap.pj.core.sk.documentoidentificacao.domain.enums.DocumentOrigin;

import java.util.UUID;

public class CustomerTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String NAME = "Hakuna";
    public static final String E_MAIL = "hakuna.matata@gmail.com";
    public static final String PHONE = "47-981050203";
    public static final boolean ACTIVE = true;
    public static final String ADDRESS = "Av. Santos Dumont, 831 - Térreo - Santo Antônio, Joinville - SC, 89218-900";
    public static final String IDENTIFICATION_DOCUMENT_NUMBER = "82711650030";
    public static final String IDENTIFICATION_DOCUMENT_NUMBER_CNPJ = "09670003000107";

    public static final String ALTER_NAME = "Jack";
    public static final String ALTER_E_MAIL = "jack.daniels@gmail.com";
    public static final String ALTER_PHONE = "11-981020304";
    public static final String ALTER_ADDRESS = "Av. Braz Leme, 1000 - Santana, São Paulo - SP, 02511-000";


    public static Customer oneCustomer() {
        return new Customer(ID, NAME, E_MAIL, PHONE, ACTIVE, ADDRESS, new IdentificationDocument(DocumentOrigin.BRASIL, IDENTIFICATION_DOCUMENT_NUMBER));
    }

    public static Customer oneCustomerCNPJ() {
        return new Customer(ID, NAME, E_MAIL, PHONE, ACTIVE, ADDRESS, new IdentificationDocument(DocumentOrigin.BRASIL, IDENTIFICATION_DOCUMENT_NUMBER_CNPJ));
    }


    public static CreateCustomerCommand onCrateCustomerCommand() {
        return new CreateCustomerCommand(NAME, E_MAIL, PHONE, ADDRESS, IDENTIFICATION_DOCUMENT_NUMBER);
    }

    public static CreateCustomerCommand onCrateCustomerCNPJCommand() {
        return new CreateCustomerCommand(NAME, E_MAIL, PHONE, ADDRESS, IDENTIFICATION_DOCUMENT_NUMBER_CNPJ);
    }

    public static UpdateCustomerCommand onUpdateCustomerCommand(UUID id) {
        return new UpdateCustomerCommand(id, ALTER_NAME, ALTER_E_MAIL, ALTER_PHONE, ALTER_ADDRESS);
    }
}
