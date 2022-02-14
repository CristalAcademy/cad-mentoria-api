package br.com.cristal.erp.controller.gerenciador;

import br.com.cristal.erp.controller.aluno.dto.AlunoResponse;
import br.com.cristal.erp.service.gerenciador.GerenciadorAprovacoesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gerenciar-candidatos")
@AllArgsConstructor
@Log4j2
public class GerenciadorAprovacoesController {

    private GerenciadorAprovacoesService gerenciadorService;

    @PostMapping("/aprovar/{id}")
    public ResponseEntity<AlunoResponse> aprovarCandidato(@PathVariable Long id){
        return ResponseEntity.ok().body(gerenciadorService.aprovar(id));
    }

    @PostMapping("/recusar/{id}")
    public ResponseEntity<Void> recusarCandidato(@PathVariable Long id){
        gerenciadorService.recusar(id);
        return ResponseEntity.ok().body(null);
    }
}
