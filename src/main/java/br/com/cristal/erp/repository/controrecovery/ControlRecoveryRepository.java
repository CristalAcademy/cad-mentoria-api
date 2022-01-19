package br.com.cristal.erp.repository.controrecovery;

import br.com.cristal.erp.repository.controrecovery.model.ControlRecovery;
import br.com.cristal.erp.repository.controrecovery.model.ControlRecoveryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ControlRecoveryRepository extends JpaRepository<ControlRecovery, ControlRecoveryId> {

    List<ControlRecovery> findByIdHash(String hash);
}
