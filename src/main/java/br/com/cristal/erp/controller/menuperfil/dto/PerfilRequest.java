package br.com.cristal.erp.controller.menuperfil.dto;

import br.com.cristal.erp.repository.usuario.model.Perfil;
import lombok.Data;

@Data
public class PerfilRequest {
    private Perfil perfil;
}
