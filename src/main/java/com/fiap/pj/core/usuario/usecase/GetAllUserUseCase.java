package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.adapter.in.api.dto.UserDto;
import com.fiap.pj.core.usuario.adapter.in.api.request.GetAlUserRequest;
import org.springframework.data.domain.Slice;

public interface GetAllUserUseCase {

    Slice<UserDto> handle(GetAlUserRequest request);
}
