package com.hc.agenda.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hc_rdvDispo")
public class RendezVousDispo { ///////// A COMPLETER
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RdvId;
    @Column(length = 1000)
    private String iCalContent;
    private String professionnel;
    private String profession;
    private int dureeDeUnRdv;
}
