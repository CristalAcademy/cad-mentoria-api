package br.com.cristal.erp.service.usuario;

import br.com.cristal.erp.controller.candidato.dto.CandidatoRequestUser;
import br.com.cristal.erp.controller.usuario.dto.UsuarioPostRequestBody;
import br.com.cristal.erp.controller.usuario.dto.UsuarioResponseBody;
import br.com.cristal.erp.mapper.UsuarioMapper;
import br.com.cristal.erp.repository.usuario.UsuarioRepository;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseBody cadastrarUsuario(CandidatoRequestUser postRequest) {
        Usuario usuarioToBeSaved = UsuarioMapper.INSTANCE.toUsuario(postRequest);

        //
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String cripSenha = encoder.encode(usuarioToBeSaved.getSenha());

        usuarioToBeSaved.setSenha(cripSenha);
        //

        usuarioToBeSaved.setPerfil(Perfil.CANDIDATO);

        Usuario savedUsuario = usuarioRepository.save(usuarioToBeSaved);
        return UsuarioMapper.INSTANCE.toResponseBody(savedUsuario);
    }
}
