package br.com.cristal.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class StartApplicationERP {
    public static void main(String[] args) {
        SpringApplication.run(StartApplicationERP.class, args);
    }
}
