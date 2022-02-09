package br.com.cristal.erp.controller.usuario;

import br.com.cristal.erp.controller.usuario.dto.UsuarioResponseBody;
import br.com.cristal.erp.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
@RequiredArgsConstructor
public class UserInfoController {


    private final UsuarioService usuarioService;

    @GetMapping(value = "/me")
    public ResponseEntity<UsuarioResponseBody> buscarUsuario() {

        return ResponseEntity.ok(usuarioService.buscarUsuario());

    }
}
