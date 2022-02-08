package br.com.cristal.erp.controller.candidato.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoRequestUser {
    private String nomeCompleto;
    private String email;
    private String senha;
}
