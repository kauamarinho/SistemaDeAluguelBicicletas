package org.example.repository;

import org.example.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    void save(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(int id);
}
