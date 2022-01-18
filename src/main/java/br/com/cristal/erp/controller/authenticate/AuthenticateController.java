package br.com.cristal.erp.controller.authenticate;

import br.com.cristal.erp.config.model.JwtRequest;
import br.com.cristal.erp.config.model.JwtResponse;
import br.com.cristal.erp.config.usuarioConfig.CustomUserDetails;
import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.util.JWTUtility;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticateController {

    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            jwtRequest.getNomeusuario(),
                            jwtRequest.getSenha()
                    )
            );
        } catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getNomeusuario());

        final String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
