package br.com.cristal.erp.controller.aluno;

import br.com.cristal.erp.controller.aluno.dto.AlunoRequest;
import br.com.cristal.erp.controller.aluno.dto.AlunoResponse;
import br.com.cristal.erp.repository.aluno.filter.AlunoFiltro;
import br.com.cristal.erp.service.aluno.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Log4j2
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> createAluno(@RequestBody AlunoRequest alunoRequest){
        return ResponseEntity.status(201).body(alunoService.alunoSave(alunoRequest));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> buscarComFiltro(AlunoFiltro filtro){
        return ResponseEntity.ok().body(alunoService.buscarComFiltro(filtro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizarAluno(
            @RequestBody AlunoRequest alunoRequest,
            @PathVariable Long id
    ){
        return ResponseEntity.status(200).body(alunoService.updateAluno(alunoRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @RequestHeader(value = "Authorization")
            @PathVariable Long id
    ){
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
