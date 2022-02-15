package br.com.cristal.erp.controller.turma.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaRequest {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFinal;
    private String foco;
    private List<Long> idAlunos;
}
