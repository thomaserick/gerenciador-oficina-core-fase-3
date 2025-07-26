package com.fiap.pj.core.customer.usecase.command;

import com.fiap.pj.core.sk.documentoidentificacao.domain.enums.DocumentOrigin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCustomerCommand extends CustomerCommand {

    @NotNull(message = "Origem do Documento de identificacão não pode ser nulo.")
    private DocumentOrigin originDocument;

    @NotBlank(message = "Numero do Documento de identificacão não pode estar vazio.")
    private String identificationDocument;

    public CreateCustomerCommand(String name, String email, String phone, boolean active, String address, DocumentOrigin originDocument, String identificationDocument) {
        super(name, email, phone, active, address);
        this.originDocument = originDocument;
        this.identificationDocument = identificationDocument;
    }
}
