package br.com.cristal.erp.util.email;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificacaoTurma {

    @Value("${envio-email.criacaoturma.message}")
    private String CONVITE;

    @Value("${retorno-email.from}")
    private String CRISTALEMAIL;

    @Value("${envio-email.assunto.message}")
    private String ASSUNTO;

    public SimpleMailMessage conviteEmail(String emailUsuario){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(CRISTALEMAIL);
        message.setTo(emailUsuario);
        message.setSubject(ASSUNTO);
        message.setText(CONVITE);

        return message;
    }
}
