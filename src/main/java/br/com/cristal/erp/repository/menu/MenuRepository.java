package br.com.cristal.erp.repository.menu;

import br.com.cristal.erp.repository.menu.model.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    public List<Menu> findAllById(Long id);

    public List<Menu> findAllByIdPai(Long id);
}
