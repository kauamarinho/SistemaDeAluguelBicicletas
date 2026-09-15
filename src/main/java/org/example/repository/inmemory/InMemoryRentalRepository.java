package org.example.repository.inmemory;

import org.example.domain.model.Rental;
import org.example.repository.RentalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRentalRepository implements RentalRepository {

    private List<Rental> rentals = new ArrayList<>();

    @Override
    public void save(Rental rental) {
        rentals.add(rental);
    }

    @Override
    public List<Rental> findAll() {
        return rentals;
    }

    @Override
    public Optional<Rental> findById(int id) {
        for (Rental r : rentals) {
            if (r.getId() == id) return Optional.of(r);
        }
        return Optional.empty();
    }
}
