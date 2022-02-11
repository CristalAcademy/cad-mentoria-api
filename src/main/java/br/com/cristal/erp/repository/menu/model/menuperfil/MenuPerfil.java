package br.com.cristal.erp.repository.menu.model.menuperfil;

import br.com.cristal.erp.repository.usuario.model.Perfil;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MenuPerfil {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;
    private Long idMenu;
}
