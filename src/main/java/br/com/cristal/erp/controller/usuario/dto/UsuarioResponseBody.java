package br.com.cristal.erp.controller.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class UsuarioResponseBody {
    private String id;
    private String nomecompleto;
    private String email;
}
