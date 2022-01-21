package br.com.cristal.erp.controller.authenticate;

import br.com.cristal.erp.config.model.JwtRequest;
import br.com.cristal.erp.config.model.JwtResponse;
import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.exception.AcessDeniedException;
import br.com.cristal.erp.util.JWTUtility;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@AllArgsConstructor
public class AuthenticateController {

    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getSenha()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new AcessDeniedException("Falha na autenticação");
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getEmail());

        final String token = jwtUtility.generateToken(userDetails);

        final String perfil = customUserDetailsService
                .loadUserByUsernameAndReturnsUsuario(jwtRequest.getEmail())
                .getPerfil().toString();

        return new JwtResponse(
                userDetails.getUsername(),
                token,
                perfil,
                Arrays.asList(userDetails.getAuthorities().toArray())
        );
    }
}
