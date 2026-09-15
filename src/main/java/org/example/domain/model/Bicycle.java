package org.example.domain.model;

import org.example.domain.enums.BicycleStatus;

public class Bicycle implements Registrable {

    private int id;
    private String model;
    private BicycleStatus status;
    private double hourlyRate;

    public Bicycle(int id, String model, BicycleStatus status, double hourlyRate) {
        this.id = id;
        this.model = model;
        this.status = status;
        this.hourlyRate = hourlyRate;
    }

    public boolean isAvailable() {
        return status == BicycleStatus.AVAILABLE;
    }

    public void changeStatus(BicycleStatus status) {
        this.status = status;
    }

    public void changeHourlyRate(double newHourlyRate) {
        this.hourlyRate = newHourlyRate;
    }

    @Override
    public String displayData() {
        return "ID: " + id + " | Model: " + model + " | Status: " + status + " | Hourly rate: $" + hourlyRate;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getName() { return model; }

    public String getModel() { return model; }
    public BicycleStatus getStatus() { return status; }
    public double getHourlyRate() { return hourlyRate; }
}
