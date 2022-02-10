package br.com.cristal.erp.service.menu;

import br.com.cristal.erp.controller.menu.dto.MenuRequest;
import br.com.cristal.erp.exception.BadRequestsException;
import br.com.cristal.erp.mapper.MenuMapper;
import br.com.cristal.erp.repository.menu.MenuRepository;
import br.com.cristal.erp.repository.menu.model.menu.Menu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MenuLinker {

    private MenuRepository menuRepository;

    public Menu findMenuById(Long idPai) throws BadRequestsException {
        return menuRepository.findById(idPai).orElseThrow(
                () -> new BadRequestsException("O Menu pai não existe"));
    }

    public Menu retornaInstanciaMenu(MenuRequest request) {
        return MenuMapper.INSTANCE.toMenu(request);
    }

    public Menu validateMenu(MenuRequest request) {

        if(request.getIdPai() == null){
            return retornaInstanciaMenu(request);
        }

        findMenuById(request.getIdPai());
        return retornaInstanciaMenu(request);
    }
}
