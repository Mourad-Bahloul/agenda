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
@Table(name = "hc_rdvDispo")
public class RendezVousDispo {
    @Id
    private String RdvId;
    private String professionnel;
    @Temporal(TemporalType.DATE)
    private Date dateDisponible;
    private String description;
}
