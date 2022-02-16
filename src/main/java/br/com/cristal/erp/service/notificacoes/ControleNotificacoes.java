package br.com.cristal.erp.service.notificacoes;

import br.com.cristal.erp.util.email.EmailNotificacaoTurma;
import br.com.cristal.erp.util.email.EmailRetorno;
import br.com.cristal.erp.util.email.EmailSender;
import br.com.cristal.erp.util.email.dto.OpcaoCandidato;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ControleNotificacoes {

    private EmailRetorno emailRetorno;
    private EmailSender emailSender;
    private EmailNotificacaoTurma emailNotificacaoTurma;

    public void notificarRecusa(String emailUsuario){
        SimpleMailMessage emailToBeSend = emailRetorno.criarEmail(
                emailUsuario,
                OpcaoCandidato.RECUSAR
        );

        emailSender.send(emailToBeSend);
    }

    public void notificarAceite(String emailUsuario){
        SimpleMailMessage emailToBeSend = emailRetorno.criarEmail(
                emailUsuario,
                OpcaoCandidato.ACEITAR
        );

        emailSender.send(emailToBeSend);
    }

    public void notificarTurma(String emailUsuario){
        SimpleMailMessage email = emailNotificacaoTurma.conviteEmail(emailUsuario);

        emailSender.send(email);
    }

}
