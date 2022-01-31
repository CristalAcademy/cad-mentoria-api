package br.com.cristal.erp.controller.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioPostRequestBody {
    private String nomecompleto;
    private String email;
    private String senha;
}
