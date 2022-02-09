package br.com.cristal.erp.controller.menu.dto;

import br.com.cristal.erp.repository.menu.model.IconType;
import lombok.Data;

@Data
public class MenuRequest {
    private String titulo;
    private String icon;
    private IconType iconType;
    private String rota;
    private Long idPai;
}
