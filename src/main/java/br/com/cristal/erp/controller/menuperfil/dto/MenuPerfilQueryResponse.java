package br.com.cristal.erp.controller.menuperfil.dto;

import br.com.cristal.erp.repository.menu.model.menu.Menu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuPerfilQueryResponse {
    private Menu menuPrincipal;
    private List<Menu> subMenus;
}
