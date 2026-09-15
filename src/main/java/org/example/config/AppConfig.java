package org.example.config;

import org.example.domain.model.Administrator;
import org.example.domain.model.Employee;
import org.example.repository.BicycleRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.RentalRepository;
import org.example.repository.ReservationRepository;
import org.example.repository.inmemory.InMemoryBicycleRepository;
import org.example.repository.inmemory.InMemoryCustomerRepository;
import org.example.repository.inmemory.InMemoryRentalRepository;
import org.example.repository.inmemory.InMemoryReservationRepository;
import org.example.service.BicycleService;
import org.example.service.CustomerService;
import org.example.service.RentalService;
import org.example.service.PaymentService;
import org.example.service.ReservationService;

/**
 * Centralizes the instantiation and wiring of the application's dependencies:
 * repositories, services and the fixed actors of the system (employee/administrator).
 * Today everything is assembled in memory; in a future migration to Spring Boot,
 * these instances would tend to become beans managed by the container.
 */
public class AppConfig {

    private final Employee employee;
    private final Administrator administrator;

    private final BicycleRepository bicycleRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final RentalRepository rentalRepository;

    private final BicycleService bicycleService;
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private final RentalService rentalService;
    private final PaymentService paymentService;

    public AppConfig() {
        this.employee = new Employee(1, "Carlos");
        this.administrator = new Administrator(2, "Marcos");

        this.bicycleRepository = new InMemoryBicycleRepository();
        this.customerRepository = new InMemoryCustomerRepository();
        this.reservationRepository = new InMemoryReservationRepository();
        this.rentalRepository = new InMemoryRentalRepository();

        this.bicycleService = new BicycleService(bicycleRepository);
        this.customerService = new CustomerService(customerRepository);
        this.reservationService = new ReservationService(reservationRepository);
        this.rentalService = new RentalService(rentalRepository);
        this.paymentService = new PaymentService();
    }

    public Employee getEmployee() {
        return employee;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public BicycleService getBicycleService() {
        return bicycleService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }

    public RentalService getRentalService() {
        return rentalService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }
}
