package br.com.cristal.erp.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InternalServerErrorExceptionDetails {
    int status;
    String title;
    String message;
}
