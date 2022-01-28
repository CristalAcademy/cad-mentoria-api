package br.com.cristal.erp.controller.candidato.dto;

import br.com.cristal.erp.repository.usuario.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoRequestUser {
    private String nome;
    private String email;
    private String senha;
}
