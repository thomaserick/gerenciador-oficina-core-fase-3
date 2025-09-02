package com.fiap.pj.core.veiculo.app;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.veiculo.adapter.out.db.VeiculoRepositoryJpa;
import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoPertenceAoClienteException;
import com.fiap.pj.core.veiculo.usecase.command.RemoverVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import com.fiap.pj.infra.cliente.persistence.ClienteRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoverVeiculoClienteServiceTest {

    @Captor
    ArgumentCaptor<Cliente> clienteArgumentCaptor;
    @Captor
    ArgumentCaptor<Veiculo> veiculoArgumentCaptor;

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;
    @Mock
    private VeiculoRepositoryJpa veiculoRepositoryJpa;

    @InjectMocks
    private RemoverVeiculoClienteService removerVeiculoClienteService;

    @Test
    void deveRemoverVeiculoDoCliente() {
        var cliente = ClienteTestFactory.umCliente();
        var veiculo = VeiculoTestFactory.umVeiculo(cliente.getId());
        cliente.adicionarVeiculo(veiculo);

        when(clienteRepositoryJpa.findByIdOrThrowNotFound(cliente.getId())).thenReturn(cliente);
        when(veiculoRepositoryJpa.findByIdOrThrowNotFound(veiculo.getId())).thenReturn(veiculo);

        var command = new RemoverVeiculoClienteCommand(cliente.getId(), veiculo.getId());
        removerVeiculoClienteService.handle(command);

        verify(clienteRepositoryJpa).save(clienteArgumentCaptor.capture());
        verify(veiculoRepositoryJpa).delete(veiculoArgumentCaptor.capture());

        Cliente clienteUpdated = clienteArgumentCaptor.getValue();
        Veiculo veiculoDeleted = veiculoArgumentCaptor.getValue();

        assertNotNull(clienteUpdated);
        assertNotNull(veiculoDeleted);
        assertThat(clienteUpdated.getVeiculos()).isEmpty();
        assertThat(veiculoDeleted.getId()).isEqualTo(veiculo.getId());
    }

    @Test
    void deveRetornarVeiculoNaoPertenceAoClienteException() {
        var cliente = ClienteTestFactory.umCliente();
        var veiculo = VeiculoTestFactory.umVeiculo(UUID.randomUUID()); // Veículo de outro cliente

        when(clienteRepositoryJpa.findByIdOrThrowNotFound(cliente.getId())).thenReturn(cliente);
        when(veiculoRepositoryJpa.findByIdOrThrowNotFound(veiculo.getId())).thenReturn(veiculo);

        var command = new RemoverVeiculoClienteCommand(cliente.getId(), veiculo.getId());
        var thrown = catchThrowable(() -> removerVeiculoClienteService.handle(command));

        assertThat(thrown).isInstanceOf(VeiculoNaoPertenceAoClienteException.class);
    }
} 