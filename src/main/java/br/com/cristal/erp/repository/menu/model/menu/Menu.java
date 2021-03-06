package br.com.cristal.erp.repository.menu.model.menu;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Menu {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String titulo;
    private String icon;
    private IconType iconType;
    private String rota;
    private Long idPai;
}
