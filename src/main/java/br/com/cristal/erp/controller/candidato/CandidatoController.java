package br.com.cristal.erp.controller.candidato;

import br.com.cristal.erp.controller.candidato.dto.CandidatoPostRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoPutRequestBody;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.repository.candidato.CandidatoRepository;
import br.com.cristal.erp.repository.candidato.model.Candidato;
import br.com.cristal.erp.service.candidato.CandidatoService;
import br.com.cristal.erp.service.candidato.mappers.CandidatoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoRepository candidatoRepository;
    private final CandidatoService candidatoService;
    private final CandidatoMapper candidatoMapper;

    @PostMapping
    // TODO Criar classe CandidatoResponse para evitar devolver Entidade de negocios
    public ResponseEntity<Candidato> create(@RequestBody CandidatoPostRequestBody request){

        // TODO retirar regra de negocio do controler e adicionar ao service

        Candidato candidato = candidatoMapper.mapearTabelaCandidato(request);
        // TODO tirar esse espaço de linha

        candidato = candidatoRepository.save(candidato);
        // TODO tirar esse espaço de linha
        return ResponseEntity.status(201).body(candidato);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Candidato> buscarId(@PathVariable Long id){

        // TODO retirar regra de negocio do controler
        Candidato candidato = candidatoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato Não Encontrado")); // TODO CRIAR excecao customizada para o front end

        return ResponseEntity.status(200).body(candidato);
    }

    @GetMapping
    public ResponseEntity<List<Candidato>> buscarTodos(){

        // TODO retirar regra de negocio do controler
        List<Candidato> candidatoes = candidatoRepository
                .findAll();
        return ResponseEntity.status(200).body(candidatoes);
    }

    @PutMapping(value = "/{id}")
    // TODO Criar classe CandidatoResponse para evitar devolver Entidade de negocios
    public ResponseEntity<CandidatoResponseBody> replace(@RequestBody CandidatoPutRequestBody candidatoPutRequestBody, @PathVariable Long id){

        // TODO retirar regra de negocio do controler e adicionar ao service

//        candidatoService.replace(candidatoPutRequestBody);

        // TODO CRIAR excecao customizada para o front end

        return ResponseEntity.status(200).body(candidatoService.replace(candidatoPutRequestBody));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        Candidato candidatoToBeDeleted = candidatoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato Não Encontrado!")); // TODO CRIAR excecao customizada para o front end  - ControlAdviced

        candidatoRepository.delete(candidatoToBeDeleted);

        return ResponseEntity.noContent().build();
    }

}
