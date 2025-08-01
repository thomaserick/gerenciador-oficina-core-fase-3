package com.fiap.pj.core.user.usecase;

import com.fiap.pj.core.user.adapter.in.api.request.GetAlUserRequest;
import com.fiap.pj.core.user.adapter.in.api.response.UserReponse;
import com.fiap.pj.infra.api.Slice;

public interface GetAllUserUseCase {

    Slice<UserReponse> handle(GetAlUserRequest request);
}
