package br.com.cristal.erp.service.menuperfil;

import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilQueryResponse;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.repository.menu.MenuPerfilRepository;
import br.com.cristal.erp.repository.menu.MenuRepository;
import br.com.cristal.erp.repository.menu.model.menu.Menu;
import br.com.cristal.erp.repository.menu.model.menuperfil.MenuPerfil;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class MenuPerfilConsulta {

    private MenuPerfilRepository menuPerfilRepository;
    private MenuRepository menuRepository;

    public Menu findMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new BadRequestsException("Menu não encontrado!"));
    }

    public List<MenuPerfilQueryResponse> vincularMenus(List<Menu> menusPai){
        return menusPai.stream()
                .map(paiFilho -> vincularPaiFilho(paiFilho))
                .collect(Collectors.toList());
    }

    public MenuPerfilQueryResponse vincularPaiFilho(Menu menuPai){
        return MenuPerfilQueryResponse
                .builder()
                .menuPrincipal(menuPai)
                .subMenus(filtrarSubMenus(menuPai.getId()))
                .build();
    }

    public List<Menu> filtrarSubMenus(Long id){
        return menuRepository.findAllByIdPai(id);
    }

    public List<Menu> filtrarMenusPai(List<Menu> allMenus){
        return allMenus
                .stream()
                .filter(item -> item.getIdPai() == null)
                .collect(Collectors.toList());
    }

    public List<Menu> pegarTodosMenus(List<MenuPerfil> menusPorPerfil){
        return menusPorPerfil
                .stream()
                .map(item -> findMenuById(item.getIdMenu()))
                .collect(Collectors.toList());
    }

    public List<MenuPerfil> pegarRelacionamentos(Perfil perfil) {
        return menuPerfilRepository.findAllByPerfil(perfil)
                .orElseThrow(() -> new BadRequestsException("Perfil Inexistente"));
    }

    public List<MenuPerfilQueryResponse> listarMenus(Perfil perfil) {
        List<MenuPerfil> relacionamentos = pegarRelacionamentos(perfil);
        List<Menu> todosMenus = pegarTodosMenus(relacionamentos);
        List<Menu> menusPais = filtrarMenusPai(todosMenus);
        return vincularMenus(menusPais);
    }
}