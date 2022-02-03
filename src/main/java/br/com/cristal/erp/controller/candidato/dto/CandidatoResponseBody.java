package br.com.cristal.erp.controller.candidato.dto;

import br.com.cristal.erp.repository.candidato.model.enums.ClasseCandidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import br.com.cristal.erp.repository.usuario.model.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoResponseBody {
    private Long id;
    private String nomeCompleto;
    private String email;
    private Perfil perfil;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtNasc;
    private Boolean trabalha;
    private Boolean estuda;
    private Integer hrsDisponiveis;
    private Boolean programou;
    private ClasseCandidato classe;
    private String motivacao;
    private StatusCandidato status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate entrevista;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate disponibilidade;
}
