package br.com.cristal.erp.controller.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioRequest {
    private String nomeCompleto;
    private String email;
    private String senha;
}
