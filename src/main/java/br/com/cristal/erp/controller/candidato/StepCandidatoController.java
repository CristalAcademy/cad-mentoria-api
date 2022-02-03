package br.com.cristal.erp.controller.candidato;

import br.com.cristal.erp.controller.candidato.dto.CandidatoRequestComplemento;
import br.com.cristal.erp.controller.candidato.dto.CandidatoRequestSocial;
import br.com.cristal.erp.controller.candidato.dto.CandidatoRequestUser;
import br.com.cristal.erp.controller.candidato.dto.CandidatoResponseBody;
import br.com.cristal.erp.service.candidato.CandidatoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("candidatos")
@AllArgsConstructor
public class StepCandidatoController {

    private CandidatoService candidatoService;

    @PostMapping(value = "step/user")
    public ResponseEntity<CandidatoResponseBody> createUser(@RequestBody CandidatoRequestUser requestUser){
        return ResponseEntity.status(201).body(candidatoService.userSave(requestUser));
    }

    @PostMapping(value = "step/complemento")
    public ResponseEntity<CandidatoResponseBody> createComp(
            @RequestBody CandidatoRequestComplemento requestComp,
            @RequestHeader(value = "Authorization") String token){
        return ResponseEntity.status(201).body(candidatoService.compSave(requestComp, token));
    }

    @PostMapping(value = "step/social")
    public ResponseEntity<CandidatoResponseBody> createSocial(
            @RequestBody CandidatoRequestSocial requestSocial,
            @RequestHeader(value = "Authorization") String token  ){
        return ResponseEntity.status(201).body(candidatoService.socialSave(requestSocial, token));
    }
}
