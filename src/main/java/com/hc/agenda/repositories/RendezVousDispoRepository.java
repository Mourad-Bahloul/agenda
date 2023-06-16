package com.hc.agenda.repositories;

import com.hc.agenda.entities.RendezVousDispo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RendezVousDispoRepository extends JpaRepository<RendezVousDispo, Long> { /// A COMPLETER
    Optional<RendezVousDispo> findById(Long id);

}
