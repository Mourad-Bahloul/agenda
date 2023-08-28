package com.hc.agenda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestRdvDispoPro {

    private Long professionnelId;
    private String description;
    private Long rdvId;
    private String typeRdv;

    //debut iCal
    private Date jourDebut;
    private int heureDebut;
    private int minuteDebut;

    //fin iCal
    private Date jourFin;
    private int heureFin;
    private int minuteFin;

    //repetition
    private int dureeRdv;
    List<Boolean> jourDisponible;
}
