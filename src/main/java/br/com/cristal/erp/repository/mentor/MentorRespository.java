package br.com.cristal.erp.repository.mentor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRespository extends JpaRepository<Mentor, Long> {
}
