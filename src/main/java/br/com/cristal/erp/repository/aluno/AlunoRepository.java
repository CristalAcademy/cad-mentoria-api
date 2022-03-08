package br.com.cristal.erp.repository.aluno;

import br.com.cristal.erp.repository.aluno.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno> {
}
