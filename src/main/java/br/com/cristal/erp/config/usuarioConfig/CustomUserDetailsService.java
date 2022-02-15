package br.com.cristal.erp.config.usuarioConfig;

import br.com.cristal.erp.repository.usuario.UsuarioRepository;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.util.JWTUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final JWTUtility jwtUtility;

    public Usuario loadUserSession() throws UsernameNotFoundException {
        String email = jwtUtility.getEmailFromToken();
        return loadUserByEmailAndReturnsUsuario(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null){
            log.info("Usuário Ínvalido");
            throw new UsernameNotFoundException("Usuário Ínvalido!");
        }
        return new CustomUserDetails(usuario);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null){
            log.info("Usuário Ínvalido");
            throw new UsernameNotFoundException("Usuário Ínvalido!");
        }
        return new CustomUserDetails(usuario);
    }

    public Usuario loadUserByEmailAndReturnsUsuario(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null){
            log.info("Usuário Ínvalido");
            throw new UsernameNotFoundException("Usuário Ínvalido!");
        }
        return usuario;
    }
}
