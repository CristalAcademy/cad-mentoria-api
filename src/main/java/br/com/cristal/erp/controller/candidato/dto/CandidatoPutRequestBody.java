package br.com.cristal.erp.controller.candidato.dto;

import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoPutRequestBody {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtNasc;
    private Boolean trabalha;
    private Boolean estuda;
    private Integer hrsDisponiveis;
    private Boolean programou;
    private String classe;
    private String motivacao;
    private StatusCandidato status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate entrevista;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate disponibilidade;
}
