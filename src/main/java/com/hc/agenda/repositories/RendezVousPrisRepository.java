package com.hc.agenda.repositories;

import com.hc.agenda.entities.RendezVousPris;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RendezVousPrisRepository extends JpaRepository<RendezVousPris, String> {
    Optional<RendezVousPris> findByNameRdv(String name);
}
