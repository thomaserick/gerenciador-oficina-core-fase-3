package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.cliente.adapter.out.db.ClienteRepositoryJpa;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.orcamento.adapter.out.db.OrcamentoRepositoryJpa;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
import com.fiap.pj.core.pecainsumo.adapter.out.db.PecaInsumoRepositoryJpa;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import com.fiap.pj.core.servico.adapter.out.db.ServicoRepositoryJpa;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoPertenceAoClienteException;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import com.fiap.pj.util.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.CLIENTE_ID;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.DESCRICAO;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.HODOMENTO;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.umOrcamentoItemPecaInsumoCommand;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.umOrcamentoItemServicoCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarOrcamentoServicoTest {

    @Mock
    private OrcamentoRepositoryJpa orcamentoRepositoryJpa;

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;

    @Mock
    private ServicoRepositoryJpa servicoRepositoryJpa;

    @Mock
    private PecaInsumoRepositoryJpa pecaInsumoRepositoryJpa;

    @InjectMocks
    private CriarOrcamentoService criarOrcamentoService;

    @Test
    void deveCriarOrcamento() {

        TestSecurityConfig.setAuthentication();

        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));
        orcamento.adicionaPecaInsumo(OrcamentoTestFactory.umOrcamentoItemPecaInsumo(orcamento.getId()));

        when(orcamentoRepositoryJpa.save(any(Orcamento.class))).thenReturn(orcamento);

        var cliente = ClienteTestFactory.umCliente();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculo(cliente.getId()));

        when(clienteRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(cliente);
        when(servicoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(ServicoTestFactory.umServico());
        when(pecaInsumoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(PecaInsumoTestFactory.umPecaInsumo());

        var orcamentoCriado = criarOrcamentoService.handle(OrcamentoTestFactory.umCriarOrcamentoCommand());

        assertNotNull(orcamento);
        assertEquals(OrcamentoTestFactory.ID, orcamentoCriado.getId());
        assertEquals(DESCRICAO, orcamentoCriado.getDescricao());
        assertEquals(CLIENTE_ID, orcamentoCriado.getClienteId());
        assertEquals(OrcamentoTestFactory.VEICULO_ID, orcamentoCriado.getVeiculoId());
        assertEquals(HODOMENTO, orcamentoCriado.getHodometro());
        assertEquals(OrcamentoTestFactory.ORCAMENTO_STATUS, orcamentoCriado.getStatus());
        assertFalse(orcamento.getServicos().isEmpty());

    }

    @Test
    void deveRetonarVeiculoNaoPertenceAoClienteException() {

        var cliente = ClienteTestFactory.umCliente();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculo(cliente.getId()));

        when(clienteRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(cliente);

        var cmd = new CriarOrcamentoCommand(DESCRICAO, CLIENTE_ID, UUID.randomUUID(), null, HODOMENTO, Set.of(umOrcamentoItemServicoCommand()), Set.of(umOrcamentoItemPecaInsumoCommand()));

        var thrown = catchThrowable(() -> criarOrcamentoService.handle(cmd));
        assertThat(thrown).isInstanceOf(VeiculoNaoPertenceAoClienteException.class);

    }
}
