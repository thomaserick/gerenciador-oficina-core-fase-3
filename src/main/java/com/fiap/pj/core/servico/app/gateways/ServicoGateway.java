package com.fiap.pj.core.servico.app.gateways;

import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.infra.servico.controller.request.ListarServicoRequest;
import com.fiap.pj.infra.servico.controller.response.ServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;

import java.util.Optional;
import java.util.UUID;

public interface ServicoGateway {

    Servico salvar(Servico servico);

    void alterar(Servico servico);

    void excluir(Servico servico);

    Optional<Servico> buscarPorId(UUID id);

    Slice<ServicoResponse> listarServico(ListarServicoRequest request);


}
