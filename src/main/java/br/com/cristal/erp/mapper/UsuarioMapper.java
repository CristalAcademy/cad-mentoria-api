package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.controller.usuario.dto.UsuarioRequest;
import br.com.cristal.erp.controller.usuario.dto.UsuarioResponse;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public abstract class UsuarioMapper {
    public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    public abstract Usuario toUsuario(UsuarioRequest usuario);

    public abstract UsuarioResponse toResponseBody(Usuario usuario);

    public abstract CandidatoResponseBody userToCandidato(Usuario usuario);
}
