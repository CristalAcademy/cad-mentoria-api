package br.com.cristal.erp.config.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String nomeUsuario;
    private String token;
    private String perfil;
    private List permissoes;
}
