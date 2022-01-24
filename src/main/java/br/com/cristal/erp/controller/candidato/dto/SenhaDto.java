package br.com.cristal.erp.controller.candidato.dto;

import lombok.Data;

@Data
public class SenhaDto {
    private String senha;
    private String hash;
}
