package com.hc.agenda.repositories;

import com.hc.agenda.entities.RendezVousDispo;
import com.hc.agenda.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousDispoRepository extends JpaRepository<RendezVousDispo, Long> {
    void deleteById(Long id);

    List<RendezVousDispo> findByUser(User user);
}
