package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.usecase.AlterarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.AlterarUsuarioCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AlterarUsuarioUseCaseImpl implements AlterarUsuarioUseCase {


    @Override
    public void handle(AlterarUsuarioCommand cmd) {
//        var usuario = repository.findByIdOrThrowNotFound(cmd.getId());
//        var senha = passwordEncoder.encode(cmd.getSenha());
//        usuario.alterar(cmd.getNome(), cmd.getSobreNome(), cmd.isAtivo(), senha, cmd.getPerfis());
//        repository.save(usuario);
    }
}
