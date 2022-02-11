package br.com.cristal.erp.repository.menu;

import br.com.cristal.erp.repository.menu.model.menuperfil.MenuPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuPerfilRepository extends JpaRepository<MenuPerfil, Long> {
}
