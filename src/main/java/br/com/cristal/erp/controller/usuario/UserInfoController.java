package br.com.cristal.erp.controller.usuario;

import br.com.cristal.erp.controller.usuario.dto.UsuarioRequest;
import br.com.cristal.erp.controller.usuario.dto.UsuarioResponse;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import br.com.cristal.erp.service.usuario.UsuarioService;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios")
@RequiredArgsConstructor
public class UserInfoController {


    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest postRequest){
        return ResponseEntity.ok().body(usuarioService.cadastrarUsuario(postRequest));
    }

    @PostMapping(value = "/perfil/{id}")
    public ResponseEntity<UsuarioResponse> definePerfil(
            @RequestBody Perfil perfil,@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(usuarioService.definePerfil(perfil, id));
    }

    @GetMapping(value = "/me")
    public ResponseEntity<UsuarioResponse> buscarUsuario() {

        return ResponseEntity.ok(usuarioService.buscarUsuario());
    }
}
