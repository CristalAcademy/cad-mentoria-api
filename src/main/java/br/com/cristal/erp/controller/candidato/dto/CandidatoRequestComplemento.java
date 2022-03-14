package br.com.cristal.erp.controller.candidato.dto;

import br.com.cristal.erp.repository.candidato.model.enums.DisponibilidadeCandidato;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidatoRequestComplemento {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtNasc;
    private Boolean trabalha;
    private Boolean estuda;
    private Integer hrsDisponiveis;
    private Boolean programou;
    private DisponibilidadeCandidato disponibilidade;
}
