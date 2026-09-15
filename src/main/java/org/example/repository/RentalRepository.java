package org.example.repository;

import org.example.domain.model.Rental;
import java.util.List;
import java.util.Optional;

public interface RentalRepository {

    void save(Rental rental);

    List<Rental> findAll();

    Optional<Rental> findById(int id);
}
