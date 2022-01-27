package br.com.cristal.erp.repository.candidato.model;

import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "candidato")
public class Candidato {
    @Id
    private Long id;
    private LocalDate dtNasc;
    private Boolean trabalha;
    private Boolean estuda;
    private Integer hrsDisponiveis;
    private Boolean programou;
    private String classe;
    @Enumerated
    private StatusCandidato status;
    @Column(columnDefinition = "TEXT")
    private String motivacao;
    private LocalDate entrevista;
    private LocalDate disponibilidade;
}
