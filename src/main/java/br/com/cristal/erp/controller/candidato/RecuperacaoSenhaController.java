package br.com.cristal.erp.controller.candidato;

import br.com.cristal.erp.service.candidato.ControlRecoveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/recuperarSenha")
public class RecuperacaoSenhaController {
    private ControlRecoveryService controlRecoveryService;

    @PostMapping
    public ResponseEntity<Void> recSenha(@RequestBody String email){
        return ResponseEntity.ok(controlRecoveryService)
    }
 }
