package br.com.cristal.erp.repository.turma;

import br.com.cristal.erp.repository.aluno.model.Aluno;
import br.com.cristal.erp.repository.turma.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
