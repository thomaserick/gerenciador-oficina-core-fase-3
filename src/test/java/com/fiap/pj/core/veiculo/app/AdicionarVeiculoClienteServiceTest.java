package com.fiap.pj.core.veiculo.app;


import com.fiap.pj.core.cliente.adapter.out.db.ClienteRepositoryJpa;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.veiculo.adapter.out.db.VeiculoRepositoryJpa;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiucloPlacaDuplicadaException;
import com.fiap.pj.core.veiculo.util.factory.VehicleTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdicionarVeiculoClienteServiceTest {

    @Captor
    ArgumentCaptor<Cliente> clienteArgumentCaptor;
    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;
    @Mock
    private VeiculoRepositoryJpa veiculoRepositoryJpa;

    @InjectMocks
    private AdicionarVeiculoClienteService addVehicleToCustomerService;

    @Test
    void shouldAdicionarVeiculoToCustomer() {
        var cliente = ClienteTestFactory.oneCustomer();
        when(clienteRepositoryJpa.findByIdOrThrowNotFound(cliente.getId())).thenReturn(cliente);
        addVehicleToCustomerService.handle(VehicleTestFactory.oneAddVehicleToCustomerCommand(cliente.getId()));

        verify(clienteRepositoryJpa).save(clienteArgumentCaptor.capture());
        Cliente clienteUpdated = clienteArgumentCaptor.getValue();

        assertNotNull(clienteUpdated);
        var veiculo = clienteUpdated.getVeiculos().stream().findFirst().orElse(null);
        assertNotNull(veiculo);
        assertEquals(VehicleTestFactory.PLATE, veiculo.getPlaca());
        assertEquals(VehicleTestFactory.MAKE, veiculo.getMarca());
        assertEquals(VehicleTestFactory.MODEL, veiculo.getModelo());
        assertEquals(VehicleTestFactory.YEAR, veiculo.getAno());
    }

    @Test
    void shouldReturnVehiclePlateDuplicateException() {
        var veiculo = VehicleTestFactory.oneVehicle(ClienteTestFactory.ID);
        Mockito.when(veiculoRepositoryJpa.existsByPlaca(veiculo.getPlaca())).thenReturn(true);
        var thrown = catchThrowable(() -> addVehicleToCustomerService.handle(VehicleTestFactory.oneAddVehicleToCustomerCommand(ClienteTestFactory.ID)));
        assertThat(thrown).isInstanceOf(VeiucloPlacaDuplicadaException.class);
    }

}
