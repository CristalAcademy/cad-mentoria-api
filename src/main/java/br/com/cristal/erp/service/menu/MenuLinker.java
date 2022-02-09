package br.com.cristal.erp.service.menu;

import br.com.cristal.erp.controller.menu.dto.MenuRequest;
import br.com.cristal.erp.mapper.MenuMapper;
import br.com.cristal.erp.repository.menu.MenuRepository;
import br.com.cristal.erp.repository.menu.model.Menu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MenuLinker {

    private MenuRepository menuRepository;

    public Boolean findMenuById(Long idPai) {
        boolean isMenuSaved = menuRepository.findById(idPai).isPresent();
        return isMenuSaved;
    }

    public Menu retornaInstanciaMenu(MenuRequest request) {
        return MenuMapper.INSTANCE.toMenu(request);
    }

    public Menu validateMenu(MenuRequest request) {
        if (findMenuById(request.getIdPai())) {
            return retornaInstanciaMenu(request);
        }
        return retornaInstanciaMenu(request);
    }
}
