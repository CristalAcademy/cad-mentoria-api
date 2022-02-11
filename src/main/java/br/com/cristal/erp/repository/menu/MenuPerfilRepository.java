package br.com.cristal.erp.repository.menu;

import br.com.cristal.erp.repository.menu.model.menuperfil.MenuPerfil;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuPerfilRepository extends JpaRepository<MenuPerfil, Long> {
    Optional<List<MenuPerfil>> findAllByPerfil(Perfil perfil);
}
