package org.example.domain.model;

import org.example.domain.enums.PaymentMethod;

public class Payment {

    private int id;
    private Rental rental;
    private double amount;
    private PaymentMethod paymentMethod;
    private String status;

    public Payment(int id, Rental rental, PaymentMethod paymentMethod) {
        this.id = id;
        this.rental = rental;
        this.amount = rental.getTotalAmount();
        this.paymentMethod = paymentMethod;
        this.status = "Confirmed";
    }

    public String generateReceipt() {
        return "RECEIPT"
                + "\nCustomer: " + rental.getCustomer().getName()
                + "\nBicycle: " + rental.getBicycle().getModel()
                + "\nPayment method: " + paymentMethod
                + "\nAmount paid: $" + amount
                + "\nStatus: " + status;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
