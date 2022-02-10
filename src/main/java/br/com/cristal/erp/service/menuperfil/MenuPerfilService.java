package br.com.cristal.erp.service.menuperfil;

import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilRequest;
import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilResponse;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.mapper.MenuPerfilMapper;
import br.com.cristal.erp.repository.menu.MenuPerfilRepository;
import br.com.cristal.erp.repository.menu.model.menu.Menu;
import br.com.cristal.erp.repository.menu.model.menuperfil.MenuPerfil;
import br.com.cristal.erp.service.menu.MenuLinker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuPerfilService {

    private MenuPerfilRepository menuPerfilRepository;
    private MenuLinker menuLinker;

    public void verificaMenu(Long idMenu){
        if(menuLinker.findMenuById(idMenu).getIdPai() != null){
            throw new BadRequestsException("Apenas Menus Pai pode ser Vinculados");
        }
    }

    public MenuPerfilResponse save(MenuPerfilRequest request){
        verificaMenu(request.getIdMenu());
        MenuPerfil menuPerfilToBeSaved = MenuPerfilMapper.INSTANCE.toMenuPerfil(request);
        MenuPerfil savedMenu = menuPerfilRepository.save(menuPerfilToBeSaved);
        return MenuPerfilMapper.INSTANCE.toMenuPerfilResponse(savedMenu);
    }
}
