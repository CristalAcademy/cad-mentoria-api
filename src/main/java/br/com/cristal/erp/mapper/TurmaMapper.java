package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.turma.dto.TurmaRequest;
import br.com.cristal.erp.controller.turma.dto.TurmaResponse;
import br.com.cristal.erp.repository.turma.model.Turma;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TurmaMapper {
    public static final TurmaMapper INSTANCE = Mappers.getMapper(TurmaMapper.class);

    public abstract Turma toTurma(TurmaRequest turmaRequest);

    public abstract TurmaResponse toResponseTurma(Turma turma);

}
