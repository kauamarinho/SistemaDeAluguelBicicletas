package org.example.service;

import org.example.domain.model.Bicycle;
import org.example.domain.enums.BicycleStatus;
import org.example.repository.BicycleRepository;
import java.util.List;

public class BicycleService {

    private BicycleRepository bicycleRepository;
    private int nextId = 1;

    public BicycleService(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    public Bicycle registerBicycle(String model, double hourlyRate) {
        Bicycle bicycle = new Bicycle(nextId++, model, BicycleStatus.AVAILABLE, hourlyRate);
        bicycleRepository.save(bicycle);
        return bicycle;
    }

    public List<Bicycle> findAll() {
        return bicycleRepository.findAll();
    }

    public Bicycle findById(int id) {
        return bicycleRepository.findById(id).orElse(null);
    }
}
