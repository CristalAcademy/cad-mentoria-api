package br.com.cristal.erp.controller.menu;

import br.com.cristal.erp.config.annotations.isAdmin;
import br.com.cristal.erp.controller.menu.dto.MenuRequest;
import br.com.cristal.erp.controller.menu.dto.MenuResponse;
import br.com.cristal.erp.service.menu.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@AllArgsConstructor
public class MenuController{

    private MenuService menuService;

    @isAdmin
    @PostMapping("/create")
    public ResponseEntity<MenuResponse> save(@RequestBody MenuRequest request){
        return ResponseEntity.ok().body(menuService.save(request));
    }
}
