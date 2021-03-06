package br.com.cristal.erp.controller.usuario;

import br.com.cristal.erp.controller.usuario.dto.EmailDto;
import br.com.cristal.erp.controller.usuario.dto.SenhaDto;
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
    public ResponseEntity<Void> recSenha(@RequestBody EmailDto emailDto){
        return controlRecoveryService.recuperarSenha(emailDto.getEmail());
    }

    @PatchMapping("/confirmar")
    public ResponseEntity<Void> confSenha(@RequestBody SenhaDto senhaDto){
        controlRecoveryService.confSenha(senhaDto);
        return ResponseEntity.ok().body(null);
    }
 }
