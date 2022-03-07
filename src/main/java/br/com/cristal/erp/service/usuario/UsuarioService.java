package br.com.cristal.erp.service.usuario;

import br.com.cristal.erp.config.usuarioConfig.CustomUserDetailsService;
import br.com.cristal.erp.controller.usuario.dto.UsuarioRequest;
import br.com.cristal.erp.controller.usuario.dto.UsuarioResponse;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.mapper.UsuarioMapper;
import br.com.cristal.erp.repository.usuario.UsuarioRepository;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import br.com.cristal.erp.util.JWTUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final JWTUtility jwtUtility;
    private final UsuarioMapper usuarioMapper;

    public Usuario findUsuarioById(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestsException("Usuário não encontrado"));
    }

    public UsuarioResponse cadastrarUsuario(UsuarioRequest postRequest) {
        Usuario usuarioToBeSaved = UsuarioMapper.INSTANCE.toUsuario(postRequest);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String cripSenha = encoder.encode(usuarioToBeSaved.getSenha());

        usuarioToBeSaved.setSenha(cripSenha);

        usuarioToBeSaved.setPerfil(Perfil.INIT);

        Usuario savedUsuario = usuarioRepository.save(usuarioToBeSaved);
        return UsuarioMapper.INSTANCE.toResponseBody(savedUsuario);
    }

    public UsuarioResponse buscarUsuario() {

        Usuario usuario = customUserDetailsService.loadUserByEmailAndReturnsUsuario(
                jwtUtility.getEmailFromToken()
        );
        return usuarioMapper.toResponseBody(usuario);
    }

    public UsuarioResponse definePerfil(Perfil perfil, Long id){

        Long idToBeUpdated = jwtUtility.verifyUser(
                findUsuarioById(id), jwtUtility.getIdFromToken());

        Usuario usuarioToBeUpdated = findUsuarioById(idToBeUpdated);
        usuarioToBeUpdated.setPerfil(perfil);

        Usuario savedUsuario = usuarioRepository.save(usuarioToBeUpdated);
        return UsuarioMapper.INSTANCE.toResponseBody(savedUsuario);

    }
}
