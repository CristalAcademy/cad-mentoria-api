package br.com.cristal.erp.mapper;

import br.com.cristal.erp.controller.Aluno.dto.AlunoRequest;
import br.com.cristal.erp.controller.Aluno.dto.AlunoResponse;
import br.com.cristal.erp.repository.aluno.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AlunoMapper {

    public static final AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    public abstract Aluno toAluno(AlunoRequest alunoRequest);

    public abstract AlunoResponse toAlunoResponse(Aluno aluno);
}