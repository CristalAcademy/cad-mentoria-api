package br.com.cristal.erp.controller.menu.dto;

import br.com.cristal.erp.repository.menu.model.menu.IconType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private String titulo;
    private String icon;
    private IconType iconType;
    private String rota;
    private Long idPai;
}
