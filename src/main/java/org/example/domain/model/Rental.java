package org.example.domain.model;

import org.example.domain.enums.BicycleStatus;
import org.example.domain.enums.RentalStatus;

import java.time.LocalDate;

public class Rental {

    private int id;
    private Customer customer;
    private Bicycle bicycle;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private int hoursUsed;
    private double totalAmount;
    private RentalStatus status;

    public Rental(int id, Customer customer, Bicycle bicycle, LocalDate pickupDate) {
        this.id = id;
        this.customer = customer;
        this.bicycle = bicycle;
        this.pickupDate = pickupDate;
        this.status = RentalStatus.IN_PROGRESS;
        this.bicycle.changeStatus(BicycleStatus.RENTED);
    }

    public void finishRental(LocalDate returnDate, int hoursUsed) {
        this.returnDate = returnDate;
        this.hoursUsed = hoursUsed;
        this.totalAmount = hoursUsed * bicycle.getHourlyRate();
        this.status = RentalStatus.FINISHED;
        this.bicycle.changeStatus(BicycleStatus.AVAILABLE);
    }

    public String displayData() {
        return "Rental ID: " + id
                + " | Customer: " + customer.getName()
                + " | Bicycle: " + bicycle.getModel()
                + " | Pickup: " + pickupDate
                + " | Status: " + status;
    }

    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Bicycle getBicycle() { return bicycle; }
    public LocalDate getPickupDate() { return pickupDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public double getTotalAmount() { return totalAmount; }
    public RentalStatus getStatus() { return status; }
}
