package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.cliente.adapter.out.db.ClienteRepositoryJpa;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.orcamento.adapter.out.db.OrcamentoRepositoryJpa;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
import com.fiap.pj.core.servico.adapter.out.db.ServicoRepositoryJpa;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarOrcamentoServiceTest {

    @Mock
    private OrcamentoRepositoryJpa orcamentoRepositoryJpa;

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;

    @Mock
    private ServicoRepositoryJpa servicoRepositoryJpa;

    @InjectMocks
    private CriarOrcamentoService criarOrcamentoService;

    @Test
    void deveCriarOrcamento() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));

        when(orcamentoRepositoryJpa.save(any(Orcamento.class))).thenReturn(orcamento);

        var cliente = ClienteTestFactory.umCliente();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculo(cliente.getId()));

        when(clienteRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(cliente);
        when(servicoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(ServicoTestFactory.umServico());

        var orcamentoCriado = criarOrcamentoService.handle(OrcamentoTestFactory.umCriarOrcamentoCommand());

        assertNotNull(orcamento);
        assertEquals(OrcamentoTestFactory.ID, orcamentoCriado.getId());
        assertEquals(OrcamentoTestFactory.DESCRICAO, orcamentoCriado.getDescricao());
        assertEquals(OrcamentoTestFactory.CLIENTE_ID, orcamentoCriado.getClienteId());
        assertEquals(OrcamentoTestFactory.VEICULO_ID, orcamentoCriado.getVeiculoId());
        assertEquals(OrcamentoTestFactory.HODOMENTO, orcamentoCriado.getHodometro());
        assertEquals(OrcamentoTestFactory.ORCAMENTO_STATUS, orcamentoCriado.getStatus());
        assertFalse(orcamento.getServicos().isEmpty());

    }
}
