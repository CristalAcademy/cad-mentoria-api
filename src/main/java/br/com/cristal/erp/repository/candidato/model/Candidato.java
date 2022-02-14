package br.com.cristal.erp.repository.candidato.model;

import br.com.cristal.erp.repository.candidato.model.enums.ClasseCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "candidato")
public class Candidato {

    @Id
    private Long id;
    private LocalDate dtNasc;
    private Boolean trabalha;
    private Boolean estuda;
    private Integer hrsDisponiveis;
    private Boolean programou;
    private ClasseCandidato classe;
    @Enumerated
    private StatusCandidato status;
    @Column(columnDefinition = "TEXT")
    private String motivacao;
    private LocalDate entrevista;
    private LocalDate disponibilidade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    private Boolean ativo;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.id = usuario.getId();
    }
}
