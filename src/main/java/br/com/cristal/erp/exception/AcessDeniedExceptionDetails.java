package br.com.cristal.erp.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AcessDeniedExceptionDetails {
    private int status;
    private String title;
    private String message;
}
