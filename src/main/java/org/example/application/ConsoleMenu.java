package org.example.application;

import org.example.config.AppConfig;
import org.example.domain.exception.RentalException;
import org.example.domain.enums.PaymentMethod;
import org.example.domain.model.Administrator;
import org.example.domain.model.Bicycle;
import org.example.domain.model.Customer;
import org.example.domain.model.Employee;
import org.example.domain.model.Rental;
import org.example.domain.model.Payment;
import org.example.domain.model.Reservation;
import org.example.service.BicycleService;
import org.example.service.CustomerService;
import org.example.service.RentalService;
import org.example.service.PaymentService;
import org.example.service.ReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Console interface for the system: reads user input, calls the services
 * and displays the result. It contains no business rules - it only
 * orchestrates the interaction via terminal.
 */
public class ConsoleMenu {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Scanner scanner = new Scanner(System.in);

    private final Employee employee;
    private final Administrator administrator;

    private final BicycleService bicycleService;
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private final RentalService rentalService;
    private final PaymentService paymentService;

    public ConsoleMenu(AppConfig config) {
        this.employee = config.getEmployee();
        this.administrator = config.getAdministrator();
        this.bicycleService = config.getBicycleService();
        this.customerService = config.getCustomerService();
        this.reservationService = config.getReservationService();
        this.rentalService = config.getRentalService();
        this.paymentService = config.getPaymentService();
    }

    public void run() {

        loadInitialData();

        int option;
        do {
            showMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> registerCustomer();
                case 2 -> listCustomers();
                case 3 -> listBicycles();
                case 4 -> makeReservation();
                case 5 -> cancelReservation();
                case 6 -> rentBicycle();
                case 7 -> returnBicycle();
                case 8 -> makePayment();
                case 9 -> listRentals();
                case 0 -> System.out.println("Exiting the system...");
                default -> System.out.println("Invalid option.");
            }

        } while (option != 0);

        scanner.close();
    }

    private void showMenu() {
        System.out.println("\n=== BICYCLE RENTAL SYSTEM ===");
        System.out.println("1 - Register customer");
        System.out.println("2 - List customers");
        System.out.println("3 - List bicycles");
        System.out.println("4 - Make reservation");
        System.out.println("5 - Cancel reservation");
        System.out.println("6 - Rent bicycle");
        System.out.println("7 - Return bicycle");
        System.out.println("8 - Make payment");
        System.out.println("9 - List rentals");
        System.out.println("0 - Exit");
        System.out.print("Choose an option: ");
    }

    private void registerCustomer() {

        System.out.println("\n-- Register Customer --");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        try {

            Customer customer =
                    customerService.registerCustomer(
                            name,
                            cpf,
                            email
                    );

            System.out.println(
                    "Customer registered successfully! ID: "
                            + customer.getId()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private void listCustomers() {
        System.out.println("\n-- Registered Customers --");
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }
        for (Customer c : customers) {
            System.out.println(c.displayData());
        }
    }

    private void listBicycles() {
        System.out.println("\n-- Bicycles --");
        for (Bicycle b : bicycleService.findAll()) {
            System.out.println(b.displayData());
        }
    }

    private void makeReservation() {
        System.out.println("\n-- Make Reservation --");

        listCustomers();
        System.out.print("Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        listBicycles();
        System.out.print("Bicycle ID: ");
        int bikeId = scanner.nextInt();
        scanner.nextLine();

        Bicycle bicycle = bicycleService.findById(bikeId);
        if (bicycle == null) {
            System.out.println("Bicycle not found.");
            return;
        }

        System.out.print("Reservation date (dd/mm/yyyy): ");
        String dateText = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateText, DATE_FORMAT);
            Reservation reservation = reservationService.makeReservation(customer, bicycle, date);
            System.out.println("Reservation made successfully! ID: " + reservation.getId());
        } catch (DateTimeParseException e) {
            System.out.println("Error: invalid date format. Use dd/mm/yyyy.");
        } catch (RentalException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cancelReservation() {
        System.out.println("\n-- Cancel Reservation --");
        List<Reservation> reservations = reservationService.findAll();
        if (reservations.isEmpty()) {
            System.out.println("No reservations registered.");
            return;
        }
        for (Reservation r : reservations) {
            System.out.println(r.displayData());
        }

        System.out.print("ID of the reservation to cancel: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            reservationService.cancelReservation(id);
            System.out.println("Reservation cancelled successfully.");
        } catch (RentalException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void rentBicycle() {
        System.out.println("\n-- Rent Bicycle --");

        listCustomers();
        System.out.print("Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        listBicycles();
        System.out.print("Bicycle ID: ");
        int bikeId = scanner.nextInt();
        scanner.nextLine();

        Bicycle bicycle = bicycleService.findById(bikeId);
        if (bicycle == null) {
            System.out.println("Bicycle not found.");
            return;
        }

        System.out.print("Pickup date (dd/mm/yyyy): ");
        String dateText = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateText, DATE_FORMAT);
            Rental rental = rentalService.rentBicycle(customer, bicycle, date);
            System.out.println("Rental created successfully! ID: " + rental.getId());
        } catch (DateTimeParseException e) {
            System.out.println("Error: invalid date format. Use dd/mm/yyyy.");
        } catch (RentalException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void returnBicycle() {
        System.out.println("\n-- Return Bicycle --");
        List<Rental> rentals = rentalService.findAll();
        if (rentals.isEmpty()) {
            System.out.println("No rentals registered.");
            return;
        }
        for (Rental r : rentals) {
            System.out.println(r.displayData());
        }

        System.out.print("Rental ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Rental rental = rentalService.findById(id);
        if (rental == null) {
            System.out.println("Rental not found.");
            return;
        }

        System.out.print("Return date (dd/mm/yyyy): ");
        String dateText = scanner.nextLine();

        System.out.print("Hours used: ");
        int hours = scanner.nextInt();
        scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateText, DATE_FORMAT);
            rentalService.returnBicycle(rental, date, hours);
            System.out.println("Bicycle returned successfully!");
            System.out.println("Total amount: $" + rental.getTotalAmount());
        } catch (DateTimeParseException e) {
            System.out.println("Error: invalid date format. Use dd/mm/yyyy.");
        } catch (RentalException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void makePayment() {
        System.out.println("\n-- Make Payment --");
        List<Rental> rentals = rentalService.findAll();
        if (rentals.isEmpty()) {
            System.out.println("No rentals registered.");
            return;
        }
        for (Rental r : rentals) {
            System.out.println(r.displayData());
        }

        System.out.print("Rental ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Rental rental = rentalService.findById(id);
        if (rental == null) {
            System.out.println("Rental not found.");
            return;
        }

        System.out.print("Payment method (PIX / CASH / CREDIT_CARD / DEBIT_CARD): ");
        String methodText = scanner.nextLine();

        try {
            PaymentMethod method = PaymentMethod.valueOf(methodText.trim().toUpperCase().replace(" ", "_"));
            Payment payment = paymentService.makePayment(rental, method);
            System.out.println(payment.generateReceipt());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: invalid payment method.");
        } catch (RentalException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listRentals() {
        System.out.println("\n-- Registered Rentals --");
        List<Rental> rentals = rentalService.findAll();
        if (rentals.isEmpty()) {
            System.out.println("No rentals registered.");
            return;
        }
        for (Rental r : rentals) {
            System.out.println(r.displayData());
        }
    }

    private void loadInitialData() {
        bicycleService.registerBicycle("Caloi Elite",   15.0);
        bicycleService.registerBicycle("Monark Urbana", 12.0);
        bicycleService.registerBicycle("Sense Bike",    18.0);
        bicycleService.registerBicycle("Caloi Speed",   20.0);
        bicycleService.registerBicycle("Houston Bike",  10.0);
    }
}
