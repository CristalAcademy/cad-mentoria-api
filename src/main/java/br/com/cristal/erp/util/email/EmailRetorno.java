package br.com.cristal.erp.util.email;

import br.com.cristal.erp.util.email.dto.OpcaoCandidato;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
public class EmailRetorno {

    @Value("${retorno-email.aceite.message}")
    private String ACEITA;

    @Value("${retorno-email.recusa.message}")
    private String RECUSA;

    @Value("${retorno-email.from}")
    private String CRISTALEMAIL;

    @Value("${retorno-email.subject}")
    private String ASSUNTO;

    public SimpleMailMessage criarEmail(String emailUsuario, OpcaoCandidato opcao ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(CRISTALEMAIL);
        message.setSubject(ASSUNTO);
        message.setTo(emailUsuario);
        message.setText(opcao.getMensagem());
        return message;
    }

}
