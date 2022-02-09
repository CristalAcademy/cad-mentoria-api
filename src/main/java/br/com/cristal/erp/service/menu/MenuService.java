package br.com.cristal.erp.service.menu;

import br.com.cristal.erp.controller.menu.dto.MenuRequest;
import br.com.cristal.erp.controller.menu.dto.MenuResponse;
import br.com.cristal.erp.mapper.MenuMapper;
import br.com.cristal.erp.repository.menu.MenuRepository;
import br.com.cristal.erp.repository.menu.model.Menu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MenuService {

    private MenuMapper menuMapper;
    private MenuLinker menuLinker;
    private MenuRepository menuRepository;


    public MenuResponse save(MenuRequest request){
        Menu menuToBeSaved = menuLinker.validateMenu(request);
        Menu menuSaved = menuRepository.save(menuToBeSaved);
        return menuMapper.INSTANCE.toMenuResponse(menuSaved);
    }
}
