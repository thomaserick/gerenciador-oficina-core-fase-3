package com.fiap.pj.core.veiculo.app.gateways;

import com.fiap.pj.core.veiculo.domain.Veiculo;

import java.util.Optional;
import java.util.UUID;

public interface VeiculoGateway {

    boolean existsByPlaca(String placa);

    Optional<Veiculo> buscarPorId(UUID id);

    void excluir(Veiculo veiculo);

}
