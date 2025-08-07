package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.cliente.adapter.out.db.ClienteRepositoryJpa;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.orcamento.adapter.out.db.OrcamentoRepositoryJpa;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.AlterarOrcamentoStatusInvalidoException;
import com.fiap.pj.core.orcamento.usecase.command.AlterarOrcamentoCommand;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
import com.fiap.pj.core.pecainsumo.adapter.out.db.PecaInsumoRepositoryJpa;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import com.fiap.pj.core.servico.adapter.out.db.ServicoRepositoryJpa;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoPertenceAoClienteException;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.CLIENTE_ID_ALTERADO;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.DESCRICAO_ALTERADO;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.HODOMENTO_ALTERADO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlterarOrcamentoServicoTest {

    @Captor
    ArgumentCaptor<Orcamento> orcamentoArgumentCaptor;

    @Mock
    private OrcamentoRepositoryJpa orcamentoRepositoryJpa;

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;

    @Mock
    private ServicoRepositoryJpa servicoRepositoryJpa;

    @Mock
    private PecaInsumoRepositoryJpa pecaInsumoRepositoryJpa;

    @InjectMocks
    private AlterarOrcamentoServico alterarOrcamentoService;

    @Test
    void deveAlterarOrcamento() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));

        var cliente = ClienteTestFactory.umClienteAlterado();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculoAlterado(cliente.getId()));

        when(orcamentoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(orcamento);
        when(clienteRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(cliente);
        when(servicoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(ServicoTestFactory.umServico());
        when(pecaInsumoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(PecaInsumoTestFactory.umPecaInsumo());

        alterarOrcamentoService.handle(OrcamentoTestFactory.umAlterarOrcamentoCommand(orcamento.getId()));


        verify(orcamentoRepositoryJpa).save(orcamentoArgumentCaptor.capture());
        Orcamento orcamentoAlterado = orcamentoArgumentCaptor.getValue();

        assertNotNull(orcamento);
        assertEquals(OrcamentoTestFactory.ID, orcamentoAlterado.getId());
        assertEquals(DESCRICAO_ALTERADO, orcamentoAlterado.getDescricao());
        assertEquals(CLIENTE_ID_ALTERADO, orcamentoAlterado.getClienteId());
        assertEquals(OrcamentoTestFactory.VEICULO_ID_ALTERADO, orcamentoAlterado.getVeiculoId());
        assertEquals(HODOMENTO_ALTERADO, orcamentoAlterado.getHodometro());
        assertEquals(OrcamentoTestFactory.ORCAMENTO_STATUS, orcamentoAlterado.getStatus());
        assertFalse(orcamento.getServicos().isEmpty());

    }

    @Test
    void deveRetonarAlterarOrcamentoStatusInvalidoException() {
        var orcamento = OrcamentoTestFactory.umOrcamento(OrcamentoStatus.APROVADO);
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));

        var cliente = ClienteTestFactory.umClienteAlterado();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculoAlterado(cliente.getId()));

        when(orcamentoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(orcamento);
        when(clienteRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(cliente);

        var thrown = catchThrowable(() -> alterarOrcamentoService.handle(OrcamentoTestFactory.umAlterarOrcamentoCommand(orcamento.getId())));
        assertThat(thrown).isInstanceOf(AlterarOrcamentoStatusInvalidoException.class);

    }

    @Test
    void deveRetonarVeiculoNaoPertenceAoClienteException() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        when(orcamentoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(orcamento);

        var cliente = ClienteTestFactory.umCliente();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculo(cliente.getId()));
        when(clienteRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(cliente);
        var cmd = new AlterarOrcamentoCommand(orcamento.getId(), DESCRICAO_ALTERADO, CLIENTE_ID_ALTERADO, UUID.randomUUID(), HODOMENTO_ALTERADO, Set.of(), Set.of());

        var thrown = catchThrowable(() -> alterarOrcamentoService.handle(cmd));
        assertThat(thrown).isInstanceOf(VeiculoNaoPertenceAoClienteException.class);
    }


}
