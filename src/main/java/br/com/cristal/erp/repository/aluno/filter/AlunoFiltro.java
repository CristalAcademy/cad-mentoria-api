package br.com.cristal.erp.repository.aluno.filter;

import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class AlunoFiltro {
    private Boolean trabalha;
    private Boolean estuda;
    private Integer hrsDisponiveis;
    private Boolean programou;
    @Enumerated(EnumType.STRING)
    private StatusCandidato status;
}
