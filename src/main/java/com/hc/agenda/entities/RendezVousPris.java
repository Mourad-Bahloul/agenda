package com.hc.agenda.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hc_rdvPris")
public class RendezVousPris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RdvId;
    private String nameRdv;
    private String client;
    private String professionnel;
    private Date dateDuRendezVous;
    private int dureeRendezVous;
    private String description;
}
