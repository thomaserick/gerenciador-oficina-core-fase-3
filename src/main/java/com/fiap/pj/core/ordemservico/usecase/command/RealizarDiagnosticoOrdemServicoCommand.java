package com.fiap.pj.core.ordemservico.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class RealizarDiagnosticoOrdemServicoCommand {

    @Setter
    @JsonIgnore
    private UUID ordemServicoId;

    @NotBlank(message = "A descrição do diagnóstico deve ser preenchida.")
    private String descricao;

}