package com.fiap.pj.core.ordemservico.util.factory;

import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.ordemservico.usecase.command.CriarOrdemServicoCommand;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;

public class OrdemServicoTestFactory {


    public static CriarOrdemServicoCommand umCriarOrdemServicoCommand() {
        return new CriarOrdemServicoCommand(ClienteTestFactory.ID, VeiculoTestFactory.ID);
    }
}
