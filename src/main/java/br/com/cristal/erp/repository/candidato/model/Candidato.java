package br.com.cristal.erp.repository.candidato.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public LocalDate dtNasc;
    public Boolean trabalha;
    public Boolean estuda;
    public Integer hrsDisponiveis;
    public Boolean programou;
    public String classe;
    @Column(columnDefinition = "TEXT")
    public String motivacao;
    public LocalDate entrevista;
    public LocalDate disponibilidade;
}
