package com.hc.agenda.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoRendezVous {
    private Long rdvId;
    private String nameRdv;
    private String client;
    private String professionnel;
    @Temporal(TemporalType.DATE)
    private Date dateDuRendezVous;
    private int dureeRendezVous;
    private String description;
}
