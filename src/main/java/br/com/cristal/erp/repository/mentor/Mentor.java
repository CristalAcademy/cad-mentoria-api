package br.com.cristal.erp.repository.mentor;

import br.com.cristal.erp.repository.turma.model.Turma;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mentor {
    @Id
    private Long id;

    @OneToMany
    private List<Turma> turma;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;
}
