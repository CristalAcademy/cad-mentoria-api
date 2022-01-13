package br.com.cristal.erp.controller.candidato.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoPostRequestBody {
    public Long id;
    public Integer idade;
    public Boolean trabalha;
    public Boolean estuda;
    public Integer hrsDisponiveis;
    public Boolean programou;
    public String classeSocial;
    public String descricaoPqMereceQuer;
    public Date marcarEntrevista;
    public Integer periodoDisponivel;
}
