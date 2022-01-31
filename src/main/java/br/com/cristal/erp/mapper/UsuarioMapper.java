package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.candidato.dto.CandidatoRequestUser;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.controller.usuario.dto.UsuarioPostRequestBody;
import br.com.cristal.erp.controller.usuario.dto.UsuarioResponseBody;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class UsuarioMapper {
    public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    public abstract Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody);

    public abstract Usuario toUsuario(CandidatoRequestUser candidatoRequestUser);

    public abstract UsuarioResponseBody toResponseBody(Usuario usuario);

    public abstract CandidatoResponseBody userToCandidato(Usuario usuario);
}
