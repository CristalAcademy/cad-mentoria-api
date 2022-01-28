package br.com.cristal.erp.controller.candidato;

import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.repository.candidato.filter.CandidatoFiltro;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.repository.candidato.model.enums.StatusCandidato;
import br.com.cristal.erp.service.candidato.CandidatoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
@RequiredArgsConstructor
@Log4j2
public class CandidatoController {
    private final CandidatoService candidatoService;

    @PostMapping
    public ResponseEntity<CandidatoResponseBody> create(
            @RequestHeader(value="Authorization") String headerToken,
            @RequestBody CandidatoPostRequestBody request
    ){
        return ResponseEntity.status(201).body(candidatoService.save(headerToken, request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CandidatoResponseBody> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(candidatoService.findByIdOrThrowBadRequestExceptionReturnsCandidatoResponse(id));
    }

    @GetMapping
    public ResponseEntity<List<Candidato>> buscarComFiltro(CandidatoFiltro filtro){
        return ResponseEntity.ok().body(candidatoService.buscaComFiltro(filtro));
    }

    @GetMapping(value = "/{id}/status")
    public ResponseEntity<StatusCandidato> buscarStatus(@PathVariable Long id){
        return ResponseEntity.ok(candidatoService.statusCandidato(id));
    }

    @PutMapping
    public ResponseEntity<CandidatoResponseBody> replace(
            @RequestHeader(value="Authorization") String headerToken,
            @RequestBody CandidatoPutRequestBody candidatoPutRequestBody
    ){
        return ResponseEntity.status(200).body(candidatoService.replace(headerToken, candidatoPutRequestBody));
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Void> delete(
            @RequestHeader(value="Authorization") String headerToken,
            @PathVariable Long id
    ) {
        candidatoService.delete(headerToken, id);
        return ResponseEntity.noContent().build();
    }
}
