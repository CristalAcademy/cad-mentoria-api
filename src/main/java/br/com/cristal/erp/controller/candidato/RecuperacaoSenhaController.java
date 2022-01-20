package br.com.cristal.erp.controller.candidato;

import br.com.cristal.erp.controller.candidato.dto.EmailDto;
import br.com.cristal.erp.controller.candidato.dto.SenhaDto;
import br.com.cristal.erp.service.candidato.ControlRecoveryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping("/recuperar-senha")
@AllArgsConstructor
@NoArgsConstructor
public class RecuperacaoSenhaController {
    @Autowired
    private ControlRecoveryService controlRecoveryService;

    @PostMapping
    public ResponseEntity<Void> recSenha(@RequestBody EmailDto emailDto) throws Exception{
        controlRecoveryService.recuperarSenha(emailDto.getEmail());
        return  ResponseEntity.ok().body(null);
    }

    @PatchMapping("/confirmar")
    public ResponseEntity<Void> confSenha(@RequestBody SenhaDto senhaDto) throws Exception{
        controlRecoveryService.confSenha(senhaDto);
        return ResponseEntity.ok().body(null);
    }
 }
