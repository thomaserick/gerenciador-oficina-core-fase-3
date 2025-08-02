package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.DocumentoIdentificacaoDuplicadoException;
import com.fiap.pj.core.cliente.usecase.CriarClienteUserCase;
import com.fiap.pj.core.cliente.usecase.command.CriarClienteCommand;
import com.fiap.pj.core.sk.documentoidentificacao.domain.DocumentoIdentificacao;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CriarClienteService implements CriarClienteUserCase {

    private final ClienteDomainRepository repository;


    @Override
    public Cliente handle(CriarClienteCommand cmd) {

        if (repository.existsByIDocumentoIdentificacaoNumero(cmd.getDocumentoIdentificacao())) {
            throw new DocumentoIdentificacaoDuplicadoException();
        }

        var documentoIdentificacao = DocumentoIdentificacao.build(
                cmd.getDocumentoIdentificacao());

        var cliente = Cliente.builder()
                .id(UUID.randomUUID())
                .nome(cmd.getNome())
                .email(cmd.getEmail())
                .ativo(true)
                .telefone(cmd.getTelefone())
                .endereco(cmd.getEndereco())
                .documentoIdentificacao(documentoIdentificacao)
                .build();

        return repository.save(cliente);
    }
}
