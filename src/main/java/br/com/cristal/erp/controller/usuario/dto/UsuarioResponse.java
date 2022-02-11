package br.com.cristal.erp.controller.usuario.dto;

import br.com.cristal.erp.repository.usuario.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponse {
    private String id;
    private String nomeCompleto;
    private String email;
    private Perfil perfil;
}
