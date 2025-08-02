package com.fiap.pj.core.cliente.util.factory;


import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.usecase.command.AlterarClienteCommand;
import com.fiap.pj.core.cliente.usecase.command.CriarClienteCommand;
import com.fiap.pj.core.sk.documentoidentificacao.domain.DocumentoIdentificacao;
import com.fiap.pj.core.sk.documentoidentificacao.domain.enums.OrigemDocumento;

import java.util.UUID;

public class ClienteTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String NOME = "Hakuna";
    public static final String E_MAIL = "hakuna.matata@gmail.com";
    public static final String TELEFONE = "47-981050203";
    public static final boolean ATIVO = true;
    public static final String ENDERECO = "Av. Santos Dumont, 831 - Térreo - Santo Antônio, Joinville - SC, 89218-900";
    public static final String NUMERO_DOCUMENTO = "82711650030";
    public static final String NUMERO_DOCUMENTO_CNPJ = "09670003000107";

    public static final String ALTER_NAME = "Jack";
    public static final String ALTER_E_MAIL = "jack.daniels@gmail.com";
    public static final String ALTER_PHONE = "11-981020304";
    public static final String ALTER_ADDRESS = "Av. Braz Leme, 1000 - Santana, São Paulo - SP, 02511-000";


    public static Cliente oneCustomer() {
        return new Cliente(ID, NOME, E_MAIL, TELEFONE, ATIVO, ENDERECO, new DocumentoIdentificacao(OrigemDocumento.BRASIL, NUMERO_DOCUMENTO));
    }

    public static Cliente oneCustomerCNPJ() {
        return new Cliente(ID, NOME, E_MAIL, TELEFONE, ATIVO, ENDERECO, new DocumentoIdentificacao(OrigemDocumento.BRASIL, NUMERO_DOCUMENTO_CNPJ));
    }


    public static CriarClienteCommand onCrateCustomerCommand() {
        return new CriarClienteCommand(NOME, E_MAIL, TELEFONE, ENDERECO, NUMERO_DOCUMENTO);
    }

    public static CriarClienteCommand onCrateCustomerCNPJCommand() {
        return new CriarClienteCommand(NOME, E_MAIL, TELEFONE, ENDERECO, NUMERO_DOCUMENTO_CNPJ);
    }

    public static AlterarClienteCommand onUpdateCustomerCommand(UUID id) {
        return new AlterarClienteCommand(id, ALTER_NAME, ALTER_E_MAIL, ALTER_PHONE, ALTER_ADDRESS);
    }
}
