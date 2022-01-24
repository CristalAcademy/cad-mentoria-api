package br.com.cristal.erp.repository.controrecovery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ControlRecovery {

    @EmbeddedId
    private ControlRecoveryId id;
    private LocalDate dataExpiracao;
}
