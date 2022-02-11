package br.com.cristal.erp.controller.menuperfil.dto;

import br.com.cristal.erp.repository.usuario.model.Perfil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuPerfilRequest {
    Perfil perfil;
    Long idMenu;
}
