package org.example.service;

import org.example.domain.exception.RentalException;
import org.example.domain.model.Bicycle;
import org.example.domain.model.Customer;
import org.example.domain.model.Reservation;
import org.example.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {

    private ReservationRepository reservationRepository;
    private int nextId = 1;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation makeReservation(Customer customer, Bicycle bicycle, LocalDate reservationDate) {
        if (!bicycle.isAvailable()) {
            throw new RentalException("Bicycle is not available for reservation.");
        }
        Reservation reservation = new Reservation(nextId++, customer, bicycle, reservationDate);
        customer.addReservation(reservation);
        reservationRepository.save(reservation);
        return reservation;
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RentalException("Reservation not found."));
        reservation.cancel();
    }

    public Reservation findById(int id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
