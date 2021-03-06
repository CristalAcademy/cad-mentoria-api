package br.com.cristal.erp.controller.candidato.dto;

import br.com.cristal.erp.repository.candidato.model.enums.ClasseCandidato;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoRequestSocial {
    private ClasseCandidato classe;
    private String motivacao;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate entrevista;
}
