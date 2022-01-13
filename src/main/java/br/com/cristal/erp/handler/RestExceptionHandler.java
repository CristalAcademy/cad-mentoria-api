package br.com.cristal.erp.handler;

import br.com.cristal.erp.exception.BadRequestExceptionDetails;
import br.com.cristal.erp.exception.BadRequestsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestsException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestsException bre){
        return new ResponseEntity<>(
                BadRequestExceptionDetails
                        .builder()
                        .details(bre.getMessage())
                        .title("Bad Request Exception")
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build() , HttpStatus.BAD_REQUEST
        );
    }
}
