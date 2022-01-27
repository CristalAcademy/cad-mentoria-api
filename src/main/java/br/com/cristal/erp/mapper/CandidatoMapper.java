package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CandidatoMapper {
    public static final CandidatoMapper INSTANCE = Mappers.getMapper(CandidatoMapper.class);

    public abstract Candidato toCandidato(CandidatoPostRequestBody candidatoPostRequestBody);

    public abstract Candidato toCandidato(CandidatoPutRequestBody candidatoPutRequestBody);

    public abstract CandidatoResponseBody toResponseBody(Candidato candidato);
}
