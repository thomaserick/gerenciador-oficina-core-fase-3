package com.fiap.pj.core.service.adapter.in.api;

import com.fiap.pj.core.service.adapter.in.api.openapi.ServiceControllerOpenApi;
import com.fiap.pj.core.service.usecase.CreateServiceUseCase;
import com.fiap.pj.core.service.usecase.command.CreateServiceCommand;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServiceController.PATH)
@AllArgsConstructor
public class ServiceController implements ServiceControllerOpenApi {

    public static final String PATH = "v1/item-services";

    private final CreateServiceUseCase createServiceUseCase;

    @Override
    @PostMapping
    public ResponseEntity<Void> createService(CreateServiceCommand cmd) {
        var service = createServiceUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), service.getId());
    }
}
