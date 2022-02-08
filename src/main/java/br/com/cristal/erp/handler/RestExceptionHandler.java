package br.com.cristal.erp.handler;

import br.com.cristal.erp.exception.*;
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

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<NotFoundDetails> handlerNotFound(NotFound not){
        return new ResponseEntity<>(
                NotFoundDetails
                        .builder()
                        .details(not.getMessage())
                        .title("Not Found Exception")
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .build() , HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AcessDeniedException.class)
    public  ResponseEntity<AcessDeniedExceptionDetails> handlerAcessDeniedException(AcessDeniedException ade){
        return new ResponseEntity<>(
                AcessDeniedExceptionDetails
                        .builder()
                        .status(HttpStatus.FORBIDDEN.value())
                        .title("Forbidden")
                        .message(ade.getMessage())
                        .build() , HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public  ResponseEntity<InternalServerErrorExceptionDetails> handlerAcessDeniedException(InternalServerErrorException ise){
        return new ResponseEntity<>(
                InternalServerErrorExceptionDetails
                        .builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .title("Internal Server Error")
                        .message(ise.getMessage())
                        .build() , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
