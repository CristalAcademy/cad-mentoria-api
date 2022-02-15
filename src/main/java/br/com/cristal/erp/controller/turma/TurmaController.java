package br.com.cristal.erp.controller.turma;

import br.com.cristal.erp.controller.turma.dto.TurmaRequest;
import br.com.cristal.erp.controller.turma.dto.TurmaResponse;
import br.com.cristal.erp.service.turma.TurmaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
@Log4j2
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<TurmaResponse> createTurma(@RequestBody TurmaRequest turmaRequest){
        return ResponseEntity.status(201).body(turmaService.criarTurma(turmaRequest));
    }
}
