package br.com.cristal.erp.repository.candidato.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "candidato")
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Integer idade; // TODO trocar para data de nascimento
    public Boolean trabalha;
    public Boolean estuda;
    public Integer hrsDisponiveis; // TODO trocar para enum de horas  2 - 4 - 6 - 8
    public Boolean programou;
    public String classeSocial; // TODO trocar para enum de classe social  6 faixas (risco , vuneravel, baixo , sustentavel , medio , alto)

    // TODO adicionar modo de texto para essa coluna @Column(columnDefinition="TEXT")
    public String descricaoPqMereceQuer; // TODO mudar nome do campo para motivacao
    public Date marcarEntrevista; // TODO trocar nome do campo por entrevista
    public Integer periodoDisponivel; // TODO trocar para nome disponibilidade e tranformar em enum (Manha  - Tarde - Noite
    // )
}
