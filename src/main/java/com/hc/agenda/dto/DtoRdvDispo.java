package com.hc.agenda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoRdvDispo {
    private String RdvId;
    private String professionnel;
    List<Boolean> jourDisponible;
    private Date horaireDebut;
    private Date horaireFin;
    private int dureeRendezVous;
    private String description;
    private String profession;
}
