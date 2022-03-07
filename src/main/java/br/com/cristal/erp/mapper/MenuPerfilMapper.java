package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilRequest;
import br.com.cristal.erp.controller.menuperfil.dto.MenuPerfilResponse;
import br.com.cristal.erp.repository.menu.model.menuperfil.MenuPerfil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MenuPerfilMapper {
    public static final MenuPerfilMapper INSTANCE = Mappers.getMapper(MenuPerfilMapper.class);

    public abstract MenuPerfil toMenuPerfil(MenuPerfilRequest menuPerfilRequest);

    public abstract MenuPerfilResponse toMenuPerfilResponse(MenuPerfil menuPerfil);
}
