package com.fiap.pj.core.service.adapter.in.api;

import com.fiap.pj.core.service.adapter.in.api.openapi.ServiceControllerOpenApi;
import com.fiap.pj.core.service.adapter.in.api.request.GetAllServiceRequest;
import com.fiap.pj.core.service.adapter.in.api.response.ServiceResponse;
import com.fiap.pj.core.service.usecase.CreateServiceUseCase;
import com.fiap.pj.core.service.usecase.GetAllServiceUseCase;
import com.fiap.pj.core.service.usecase.command.CreateServiceCommand;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.api.Slice;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServiceController.PATH)
@AllArgsConstructor
public class ServiceController implements ServiceControllerOpenApi {

    public static final String PATH = "v1/item-services";

    private final CreateServiceUseCase createServiceUseCase;
    private final GetAllServiceUseCase getAllServiceUseCase;

    @Override
    @PostMapping
    public ResponseEntity<Void> createService(CreateServiceCommand cmd) {
        var service = createServiceUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), service.getId());
    }


    @Override
    @GetMapping
    public Slice<ServiceResponse> getAll(@ParameterObject GetAllServiceRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return getAllServiceUseCase.handle(filterRequest);
    }

}
