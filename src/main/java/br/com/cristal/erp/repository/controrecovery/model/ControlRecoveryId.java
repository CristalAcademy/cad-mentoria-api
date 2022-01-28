package br.com.cristal.erp.repository.controrecovery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ControlRecoveryId implements Serializable {
    private String email;
    private String hash;
}
