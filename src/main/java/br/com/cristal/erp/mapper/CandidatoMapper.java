package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.candidato.dto.*;
import br.com.cristal.erp.controller.usuario.dto.UsuarioRequest;
import br.com.cristal.erp.controller.usuario.dto.UsuarioResponse;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CandidatoMapper {
    public static final CandidatoMapper INSTANCE = Mappers.getMapper(CandidatoMapper.class);
    public abstract CandidatoResponseBody toCandidatoResponseBody(UsuarioRequest usuarioPost);

    public abstract Candidato toCandidatoComp(CandidatoRequestComplemento candidatoRequestComplemento);

    public abstract Candidato toCandidatoSocial(CandidatoRequestSocial candidatoRequestSocial);

    public abstract Candidato toCandidato(CandidatoPutRequestBody candidatoPutRequestBody);

    @Mapping(target = "nomeCompleto", source = "candidato.usuario.nomeCompleto")
    @Mapping(target = "email", source = "candidato.usuario.email")
    @Mapping(target = "perfil", source = "candidato.usuario.perfil")
    public abstract CandidatoResponseBody toResponseBody(Candidato candidato);

    public abstract CandidatoResponseBody toCandidato(UsuarioResponse usuarioResponse);
}
