package com.fiap.pj.core.orcamento.adapter.in.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.UUID;

import static com.fiap.pj.core.orcamento.domain.specification.OrcamentoSpecification.queContenhaClienteIgualA;


@Getter
@AllArgsConstructor
public class ListarOrcamentoRequest {


    private UUID clienteId;
    @Setter
    @JsonIgnore
    private Pageable pageable;

    public Specification<Orcamento> buildSpecification() {
        Specification<Orcamento> specs = Specification.allOf();

        if (Objects.nonNull(clienteId)) {
            specs = specs.and(queContenhaClienteIgualA(this.clienteId));
        }


        return specs;
    }


}
