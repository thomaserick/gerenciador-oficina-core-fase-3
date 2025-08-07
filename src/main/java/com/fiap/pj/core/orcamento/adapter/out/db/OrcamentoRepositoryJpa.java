package com.fiap.pj.core.orcamento.adapter.out.db;


import com.fiap.pj.core.orcamento.adapter.in.api.response.OrcamentoResponse;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface OrcamentoRepositoryJpa extends OrcamentoDomainRepository, Repository<Orcamento, UUID> {

    String SQL = """
            SELECT
            orc.id           AS id,
            orc.descricao    AS descricao,
            orc.hodometro    AS hodometro,
            orc.status       AS status,
            orc.dataCriacao AS dataCriacao,
            cl.id            AS clienteId,
            cl.nome          AS clienteNome,
            vei.id           AS veiculoId,
            vei.placa        AS placa,
            vei.marca        AS marca,
            vei.modelo       AS modelo,
            vei.ano          AS ano,
            os               AS servicos,
            opi              AS pecasInsumos
            FROM Orcamento orc
            INNER JOIN Cliente cl
                            ON orc.clienteId = cl.id
            INNER JOIN Veiculo vei
                            ON orc.veiculoId = vei.id
            LEFT JOIN OrcamentoItemServico os
                            ON orc.id = os.orcamentoId
            LEFT JOIN OrcamentoItemPecaInsumo opi
                            ON orc.id = opi.orcamentoId
            WHERE (cast(:clienteId as uuid) IS NULL OR orc.clienteId = :clienteId)
            """;


    @Override
    default Orcamento findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(OrcamentoNaoEncontradoException::new);
    }

    @Override
    @Query(value = SQL)
    Slice<OrcamentoResponse> findAllByClienteId(@Param("clienteId") UUID clienteId, Pageable pageable);
}