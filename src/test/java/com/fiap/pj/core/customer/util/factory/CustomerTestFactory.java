package com.fiap.pj.core.customer.util.factory;


import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.usecase.command.CreateCustomerCommand;
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
}
