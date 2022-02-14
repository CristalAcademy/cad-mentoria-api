package br.com.cristal.erp.controller.menuperfil;

import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilQueryResponse;
import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilRequest;
import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilResponse;
import br.com.cristal.erp.controller.menuperfil.dto.PerfilRequest;
import br.com.cristal.erp.service.menuperfil.MenuPerfilService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-perfil")
@AllArgsConstructor
public class MenuPerfilController {

    private MenuPerfilService menuPerfilService;

    @PostMapping("/vincular-menu")
    public ResponseEntity<MenuPerfilResponse> vincular(@RequestBody MenuPerfilRequest request){
        return ResponseEntity.ok().body(menuPerfilService.save(request));
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<MenuPerfilQueryResponse>> consultaMenu(@RequestBody PerfilRequest perfil){
        return ResponseEntity.ok().body(menuPerfilService.consultar(perfil));
    }
}
