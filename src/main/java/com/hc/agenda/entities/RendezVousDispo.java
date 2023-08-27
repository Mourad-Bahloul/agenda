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
public class RendezVousDispo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RdvId;
    @Column(length = 1000)
    private String iCalContent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String profession;
    private int dureeDeUnRdv;
}
