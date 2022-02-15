package br.com.cristal.erp.controller.turma.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaResponse {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFinal;
    private String foco;
}
