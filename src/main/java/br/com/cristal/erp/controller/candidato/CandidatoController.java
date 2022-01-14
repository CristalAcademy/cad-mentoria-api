package br.com.cristal.erp.controller.candidato;

import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
<<<<<<< HEAD
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
=======
>>>>>>> 636796ded737214e8129ae32fba7c9acc95f19b8
import br.com.cristal.erp.service.candidato.CandidatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
@RequiredArgsConstructor
public class CandidatoController {
    private final CandidatoService candidatoService;

    @PostMapping
    public ResponseEntity<CandidatoResponseBody> create(@RequestBody CandidatoPostRequestBody request){
        return ResponseEntity.status(201).body(candidatoService.save(request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CandidatoResponseBody> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(candidatoService.findByIdOrThrowBadRequestExceptionReturnsCandidatoResponse(id));
    }

    @GetMapping
    public ResponseEntity<List<CandidatoResponseBody>> buscarTodos(){
        return ResponseEntity.ok(candidatoService.listAll());
    }

    @GetMapping(value = "/{id}/status")
    public ResponseEntity<StatusCandidato> buscarStatus(@PathVariable Long id){
        return ResponseEntity.ok(candidatoService.statusCandidato(id));
    }

    @PutMapping
    public ResponseEntity<CandidatoResponseBody> replace(@RequestBody CandidatoPutRequestBody candidatoPutRequestBody){
        return ResponseEntity.status(200).body(candidatoService.replace(candidatoPutRequestBody));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        candidatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
