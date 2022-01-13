package br.com.cristal.erp.controller.candidato.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoResponseBody {
    public Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate dtNasc;
    public Boolean trabalha;
    public Boolean estuda;
    public Integer hrsDisponiveis;
    public Boolean programou;
    public String classe;
    public String motivacao;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate entrevista;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate disponibilidade;
}
