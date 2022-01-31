package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.candidato.dto.*;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CandidatoMapper {
    public static final CandidatoMapper INSTANCE = Mappers.getMapper(CandidatoMapper.class);

//    public abstract Candidato toCandidato(CandidatoPostRequestBody candidatoPostRequestBody);

    public abstract Candidato toCandidatoUser(CandidatoRequestUser candidatoRequestUser);

    public abstract Candidato toCandidatoComp(CandidatoRequestComplemento candidatoRequestComplemento);

    public abstract Candidato toCandidatoSocial(CandidatoRequestSocial candidatoRequestSocial);

    public abstract Candidato toCandidato(CandidatoPutRequestBody candidatoPutRequestBody);

    public abstract CandidatoResponseBody toResponseBody(Candidato candidato);
}
