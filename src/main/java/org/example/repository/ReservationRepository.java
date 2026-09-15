package org.example.repository;

import org.example.domain.model.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    void save(Reservation reservation);

    List<Reservation> findAll();

    Optional<Reservation> findById(int id);
}
