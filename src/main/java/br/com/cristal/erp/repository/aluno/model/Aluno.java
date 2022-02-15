package br.com.cristal.erp.repository.aluno.model;

import br.com.cristal.erp.repository.turma.model.Turma;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "aluno")
public class Aluno {

    @Id
    private Long id;
    private LocalDate dtNasc;
    private Boolean trabalha;
    private Boolean estuda;
    private Integer hrsDisponiveis;
    private Boolean programou;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.id = usuario.getId();
    }

    @ManyToMany(mappedBy = "alunos")
    List<Turma> turmas;
}
