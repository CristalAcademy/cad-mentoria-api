package br.com.cristal.erp.service.candidato;

import br.com.cristal.erp.controller.candidato.dto.SenhaDto;
import br.com.cristal.erp.repository.controrecovery.ControlRecoveryRepository;
import br.com.cristal.erp.repository.controrecovery.model.ControlRecovery;
import br.com.cristal.erp.repository.controrecovery.model.ControlRecoveryId;
import br.com.cristal.erp.repository.usuario.UsuarioRepository;
import br.com.cristal.erp.repository.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class ControlRecoveryService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ControlRecoveryRepository controlRecoveryRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value(value = "${api.links.recuperar-senha}")
    private String linkFrontEnd;

    public void recuperarSenha(String email){
        String hash = UUID.randomUUID().toString();
        String linkUser = linkFrontEnd + hash;
        ControlRecoveryId controlRecoveryId = new ControlRecoveryId(email, hash );
        ControlRecovery controlRecovery = new ControlRecovery(controlRecoveryId, LocalDate.now().plusDays(1));
        controlRecoveryRepository.save(controlRecovery);

        enviarEmail(email , linkUser);
    }

    public void enviarEmail(String email , String link) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("cristalacademysdg@gmail.com");
            message.setTo(email);
            message.setSubject("Recuperação de senha");
            message.setText("Click aqui para recuperar senha : \n " + link);
            emailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException("Email não enviado");
        }
    }

    public void confSenha(SenhaDto senhaDto) throws Exception{
        String hash = senhaDto.getHash();
        String senha = senhaDto.getSenha();

        ControlRecovery controlRecovery = controlRecoveryRepository.findById_Hash(hash);
        if(LocalDate.now().isAfter(controlRecovery.getDataExpiracao())) {
            throw new Exception("Desculpe, você não tem mais acesso a esse link");
        }
        Usuario usuario = usuarioRepository.findByEmail(controlRecovery.getId().getEmail());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String cripSenha = encoder.encode(senha);

        usuario.setSenha(cripSenha);

        usuarioRepository.save(usuario);
    }
}