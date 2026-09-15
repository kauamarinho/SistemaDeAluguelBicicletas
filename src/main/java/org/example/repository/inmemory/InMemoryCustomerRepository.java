package org.example.repository.inmemory;

import org.example.domain.model.Customer;
import org.example.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryCustomerRepository implements CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Optional<Customer> findById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) return Optional.of(c);
        }
        return Optional.empty();
    }
}
