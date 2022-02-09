package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.menu.dto.MenuRequest;
import br.com.cristal.erp.controller.menu.dto.MenuResponse;
import br.com.cristal.erp.repository.menu.model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MenuMapper {
    public static final MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);
    public abstract Menu toMenu(MenuRequest menuRequest);
    public abstract MenuResponse toMenuResponse(Menu menu);
}
