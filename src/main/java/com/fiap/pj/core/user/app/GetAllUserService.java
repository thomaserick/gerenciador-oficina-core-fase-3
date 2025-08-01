package com.fiap.pj.core.user.app;


import com.fiap.pj.core.user.adapter.in.api.request.GetAlUserRequest;
import com.fiap.pj.core.user.adapter.in.api.response.UserReponse;
import com.fiap.pj.core.user.domain.UserDomainRepository;
import com.fiap.pj.core.user.usecase.GetAllUserUseCase;
import com.fiap.pj.infra.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class GetAllUserService implements GetAllUserUseCase {

    private final UserDomainRepository repository;

    @Override
    public Slice<UserReponse> handle(GetAlUserRequest request) {
        return repository.findProjectedBy(request.buildSpecification(), request.getPageable(), UserReponse.class);
    }
}
