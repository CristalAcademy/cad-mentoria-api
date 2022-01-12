package br.com.cristal.erp.repository.candidato;

import br.com.cristal.erp.repository.candidato.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
