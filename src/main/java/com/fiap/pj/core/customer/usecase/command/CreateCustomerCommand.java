package com.fiap.pj.core.customer.usecase.command;

import com.fiap.pj.infra.validator.CPFCNPJ;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCustomerCommand extends CustomerCommand {


    @NotBlank(message = "Numero do Documento de identificacão não pode estar vazio.")
    @CPFCNPJ
    private String identificationDocument;

    public CreateCustomerCommand(String name, String email, String phone, String address, String identificationDocument) {
        super(name, email, phone, address);
        this.identificationDocument = identificationDocument;
    }
}
