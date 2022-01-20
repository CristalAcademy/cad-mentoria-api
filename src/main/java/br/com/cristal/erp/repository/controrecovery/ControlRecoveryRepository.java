package br.com.cristal.erp.repository.controrecovery;

import br.com.cristal.erp.repository.controrecovery.model.ControlRecovery;
import br.com.cristal.erp.repository.controrecovery.model.ControlRecoveryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ControlRecoveryRepository extends JpaRepository<ControlRecovery, ControlRecoveryId> {

    ControlRecovery findById_Hash(String hash);
}
