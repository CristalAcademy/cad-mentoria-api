package br.com.cristal.erp.controller.menuperfil;

import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilRequest;
import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilResponse;
import br.com.cristal.erp.service.menuperfil.MenuPerfilService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vincular-menu")
@AllArgsConstructor
public class MenuPerfilController {

    private MenuPerfilService menuPerfilService;

    @PostMapping
    public ResponseEntity<MenuPerfilResponse> vincular(@RequestBody MenuPerfilRequest request){
        return ResponseEntity.ok().body(menuPerfilService.save(request));
    }
}
