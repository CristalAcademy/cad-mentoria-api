package br.com.cristal.erp.controller.candidato.dto;

import br.com.cristal.erp.repository.candidato.model.enums.ClasseCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.DisponibilidadeCandidato;
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
    private String nome;
    private String email;
    private String senha;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtNasc;
    private Boolean trabalha;
    private Boolean estuda;
    private Integer hrsDisponiveis;
    private Boolean programou;
    private ClasseCandidato classe;
    private String motivacao;
    private StatusCandidato status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate entrevista;
    private DisponibilidadeCandidato disponibilidade;
}
