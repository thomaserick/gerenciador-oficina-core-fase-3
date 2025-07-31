package com.fiap.pj.core.estimate.adapter.in.api;

import com.fiap.pj.core.estimate.adapter.in.api.openapi.EstimateControllerOpenApi;
import com.fiap.pj.core.estimate.usecase.CreateEstimateUseCase;
import com.fiap.pj.core.estimate.usecase.command.CreateEstimateCommand;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = EstimateController.PATH)
@AllArgsConstructor
public class EstimateController implements EstimateControllerOpenApi {

    public static final String PATH = "v1/estimate";

    private final CreateEstimateUseCase createEstimateUseCase;


    @Override
    @PostMapping
    public ResponseEntity<Void> createEstimate(@Valid CreateEstimateCommand cmd) {
        var estimate = createEstimateUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), estimate.getId());
    }
}
