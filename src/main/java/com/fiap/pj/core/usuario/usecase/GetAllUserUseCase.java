package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.adapter.in.api.request.GetAlUserRequest;
import com.fiap.pj.core.usuario.adapter.in.api.response.UserReponse;
import com.fiap.pj.infra.api.Slice;

public interface GetAllUserUseCase {

    Slice<UserReponse> handle(GetAlUserRequest request);
}
