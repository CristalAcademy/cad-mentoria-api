package br.com.cristal.erp.util.email;

import br.com.cristal.erp.exception.AcessDeniedException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmailSender {

    @Autowired
    private JavaMailSender emailSender;

    public void send(SimpleMailMessage message){
        try {
            emailSender.send(message);
        } catch (Exception e) {
            throw new AcessDeniedException("Falha na autênticação");
        }
    }

}
