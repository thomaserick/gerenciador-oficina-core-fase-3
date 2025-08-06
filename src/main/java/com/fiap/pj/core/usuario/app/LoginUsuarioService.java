package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.in.api.response.LoginUsuarioResponse;
import com.fiap.pj.core.usuario.usecase.LoginUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.LoginUsuarioCommand;
import com.fiap.pj.infra.exception.CredenciaisInvalidasException;
import com.fiap.pj.infra.helpers.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class LoginUsuarioService implements LoginUsuarioUseCase {


    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public LoginUsuarioResponse handle(LoginUsuarioCommand cmd) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(cmd.email(), cmd.senha()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String jwt = jwtUtil.generateToken(cmd.email());
            return new LoginUsuarioResponse(jwt);
        } catch (AuthenticationException e) {
            throw new CredenciaisInvalidasException("Credenciais inválidas");
        }
    }
}
