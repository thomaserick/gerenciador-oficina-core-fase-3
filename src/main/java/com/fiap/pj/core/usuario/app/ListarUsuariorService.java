package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.in.api.request.ListarUsuarioRequest;
import com.fiap.pj.core.usuario.adapter.in.api.response.UsuarioReponse;
import com.fiap.pj.core.usuario.domain.UsuarioDomainRepository;
import com.fiap.pj.core.usuario.usecase.ListarUsuarioUseCase;
import com.fiap.pj.infra.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ListarUsuariorService implements ListarUsuarioUseCase {

    private final UsuarioDomainRepository repository;

    @Override
    public Slice<UsuarioReponse> handle(ListarUsuarioRequest request) {
        return repository.findProjectedBy(request.buildSpecification(), request.getPageable(), UsuarioReponse.class);
    }
}
