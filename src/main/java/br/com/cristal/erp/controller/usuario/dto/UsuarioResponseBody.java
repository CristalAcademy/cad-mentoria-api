package br.com.cristal.erp.controller.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponseBody {
    private String id;
    private String nomeCompleto;
    private String email;
}
