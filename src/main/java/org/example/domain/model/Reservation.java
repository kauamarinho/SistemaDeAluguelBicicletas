package org.example.domain.model;

import org.example.domain.enums.BicycleStatus;
import org.example.domain.enums.ReservationStatus;

import java.time.LocalDate;

public class Reservation {

    private int id;
    private Customer customer;
    private Bicycle bicycle;
    private LocalDate reservationDate;
    private ReservationStatus status;

    public Reservation(int id, Customer customer, Bicycle bicycle, LocalDate reservationDate) {
        this.id = id;
        this.customer = customer;
        this.bicycle = bicycle;
        this.reservationDate = reservationDate;
        this.status = ReservationStatus.ACTIVE;
        this.bicycle.changeStatus(BicycleStatus.RESERVED);
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
        this.bicycle.changeStatus(BicycleStatus.AVAILABLE);
    }

    public String displayData() {
        return "Reservation ID: " + id
                + " | Customer: " + customer.getName()
                + " | Bicycle: " + bicycle.getModel()
                + " | Date: " + reservationDate
                + " | Status: " + status;
    }

    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Bicycle getBicycle() { return bicycle; }
    public LocalDate getReservationDate() { return reservationDate; }
    public ReservationStatus getStatus() { return status; }
}
