package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.usecase.ListarUsuarioUseCase;
import com.fiap.pj.infra.api.Slice;
import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ListarUsuariorUseCaseImpl implements ListarUsuarioUseCase {


    @Override
    public Slice<UsuarioReponse> handle(ListarUsuarioRequest request) {
//        return repository.findProjectedBy(request.buildSpecification(), request.getPageable(), UsuarioReponse.class);
        return null;
    }
}
