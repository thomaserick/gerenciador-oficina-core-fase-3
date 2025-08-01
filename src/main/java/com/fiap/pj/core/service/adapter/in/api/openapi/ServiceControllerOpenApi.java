package com.fiap.pj.core.service.adapter.in.api.openapi;

import com.fiap.pj.core.service.adapter.in.api.request.GetAllServiceRequest;
import com.fiap.pj.core.service.adapter.in.api.response.ServiceResponse;
import com.fiap.pj.core.service.usecase.command.CreateServiceCommand;
import com.fiap.pj.infra.api.Slice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ServiceControllerOpenApi {

    @Operation(description = "Cria um novo servico", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Servico criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Servico não pode ser criado.")})
    ResponseEntity<Void> createService(@Valid @RequestBody CreateServiceCommand cmd);


    @Operation(description = "Retorna uma lista de servico.", method = "GET")
    Slice<ServiceResponse> getAll(@ParameterObject GetAllServiceRequest filterRequest, @ParameterObject Pageable pageable);

}
