package org.example.repository;

import org.example.domain.model.Bicycle;
import java.util.List;
import java.util.Optional;

public interface BicycleRepository {

    void save(Bicycle bicycle);

    List<Bicycle> findAll();

    Optional<Bicycle> findById(int id);
}
