package br.com.cristal.erp.controller.usuario;

import br.com.cristal.erp.controller.candidato.dto.CandidatoRequestUser;
import br.com.cristal.erp.controller.usuario.dto.UsuarioResponseBody;
import br.com.cristal.erp.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseBody> cadastrarUsuario(@RequestBody CandidatoRequestUser postRequest){
        return ResponseEntity.ok().body(usuarioService.cadastrarUsuario(postRequest));
    }
}
