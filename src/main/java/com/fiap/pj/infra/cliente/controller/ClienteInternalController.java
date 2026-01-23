package com.fiap.pj.infra.cliente.controller;


import com.fiap.pj.core.cliente.app.usecase.ExistsClienteByCpfUseCase;
import com.fiap.pj.core.cliente.app.usecase.command.ExistsClienteByCpfAndAtivoCommand;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interno/clientes")
@Hidden
@AllArgsConstructor
public class ClienteInternalController {

    private final ExistsClienteByCpfUseCase existsClienteByCpfUseCase;

    @GetMapping("/{cpf}")
    public boolean buscarPorCpf(@PathVariable String cpf) {
        return existsClienteByCpfUseCase.handle(new ExistsClienteByCpfAndAtivoCommand(cpf));
    }
}
