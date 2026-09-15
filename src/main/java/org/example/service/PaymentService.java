package org.example.service;

import org.example.domain.exception.RentalException;
import org.example.domain.enums.PaymentMethod;
import org.example.domain.model.Rental;
import org.example.domain.model.Payment;
import org.example.domain.enums.RentalStatus;

public class PaymentService {

    private int nextId = 1;

    public Payment makePayment(Rental rental, PaymentMethod paymentMethod) {
        if (rental.getStatus() != RentalStatus.FINISHED) {
            throw new RentalException("The rental must be finished before a payment can be made.");
        }
        Payment payment = new Payment(nextId++, rental, paymentMethod);
        return payment;
    }
}
