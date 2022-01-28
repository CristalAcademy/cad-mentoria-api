package br.com.cristal.erp.repository.candidato.filter;

import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import lombok.Data;

import javax.persistence.Enumerated;

@Data
public class CandidatoFiltro {
    private Boolean trabalha;
    private Boolean estuda;
    private Integer hrsDisponiveis;
    private Boolean programou;
    private String classe;
    @Enumerated
    private StatusCandidato status;
}
