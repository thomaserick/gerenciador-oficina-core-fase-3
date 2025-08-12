package com.fiap.pj.core.ordemservico.domain;

import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.util.factory.OrdemServicoTestFactory;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrdemServicoTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de ordem de servico.")
    void deveCriarOrdemServico() {
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(OrdemServicoStatus.CRIADA);

        assertEquals(OrdemServicoTestFactory.ID, ordemServico.getId());
        assertEquals(ClienteTestFactory.ID, ordemServico.getClienteId());
        assertEquals(UsuarioTestFactory.ID, ordemServico.getUsuarioId());
        assertEquals(VeiculoTestFactory.ID, ordemServico.getVeiculoId());
        assertEquals(OrdemServicoStatus.CRIADA, ordemServico.getStatus());

    }
}
