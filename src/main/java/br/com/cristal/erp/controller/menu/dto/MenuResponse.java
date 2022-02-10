package br.com.cristal.erp.controller.menu.dto;

import br.com.cristal.erp.repository.menu.model.menu.IconType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuResponse {
    private String id;
    private String titulo;
    private String icon;
    private IconType iconType;
    private String rota;
    private Long idPai;
}
