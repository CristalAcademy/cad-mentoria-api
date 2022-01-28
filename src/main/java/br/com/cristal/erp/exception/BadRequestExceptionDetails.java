package br.com.cristal.erp.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {
    private int status;
    private String title;
    private LocalDateTime timeStamp;
    private String details;
}
