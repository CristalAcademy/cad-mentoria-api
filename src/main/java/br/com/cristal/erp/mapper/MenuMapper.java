package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.menu.dto.MenuRequest;
import br.com.cristal.erp.controller.menu.dto.MenuResponse;
import br.com.cristal.erp.repository.menu.model.menu.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MenuMapper {
    public static final MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);
    @Mapping(source = "titulo", target = "titulo")
    public abstract Menu toMenu(MenuRequest menuRequest);
    public abstract MenuResponse toMenuResponse(Menu menu);
}
