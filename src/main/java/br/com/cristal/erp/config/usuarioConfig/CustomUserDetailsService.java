package br.com.cristal.erp.config.usuarioConfig;

import br.com.cristal.erp.repository.usuario.UsuarioRepository;
import br.com.cristal.erp.repository.usuario.model.Usuario;
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

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByNomeusuario(nome);
        if(usuario == null){
            log.info("Usuário Ínvalido");
            throw new UsernameNotFoundException("Usuário Ínvalido!");
        }
        return new CustomUserDetails(usuario);
    }

    public Usuario loadUserByUsernameAndReturnsUsuario(String nome) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByNomeusuario(nome);
        if(usuario == null){
            log.info("Usuário Ínvalido");
            throw new UsernameNotFoundException("Usuário Ínvalido!");
        }
        return usuario;
    }
}
