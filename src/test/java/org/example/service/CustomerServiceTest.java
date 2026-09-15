package org.example.service;

import org.example.domain.exception.InvalidCpfException;
import org.example.domain.exception.InvalidEmailException;
import org.example.domain.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.repository.inmemory.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void registerCustomer_withValidCpfAndEmail_shouldPersistCustomerWithIncrementalId() {
        Customer customer = customerService.registerCustomer("Ana", "123.456.789-01", "ana@email.com");

        assertEquals(1, customer.getId());
        assertEquals("Ana", customer.getName());
        assertEquals("12345678901", customer.getCpf());
        assertEquals("ana@email.com", customer.getEmail());
        assertEquals(1, customerService.findAll().size());
    }

    @Test
    void registerCustomer_withFormattedCpf_shouldNormalizePunctuation() {
        Customer customer = customerService.registerCustomer("Ana", "123.456.789-01", "ana@email.com");

        assertEquals("12345678901", customer.getCpf());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123",                  // invalid length
            "1234567890123",        // invalid length
            "abc.def.ghi-01",       // contains letters
            "1234567890a"           // contains a letter among the digits
    })
    void registerCustomer_withInvalidCpf_shouldThrowInvalidCpfException(String invalidCpf) {
        assertThrows(InvalidCpfException.class,
                () -> customerService.registerCustomer("Ana", invalidCpf, "ana@email.com"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ana-email.com",  // no @
            "ana@",           // no domain
            "@email.com",     // no user
            "ana@email"       // no TLD
    })
    void registerCustomer_withInvalidEmail_shouldThrowInvalidEmailException(String invalidEmail) {
        assertThrows(InvalidEmailException.class,
                () -> customerService.registerCustomer("Ana", "12345678901", invalidEmail));
    }

    @Test
    void registerCustomer_withInvalidCpf_shouldNotPersistCustomer() {
        assertThrows(InvalidCpfException.class,
                () -> customerService.registerCustomer("Ana", "123", "ana@email.com"));

        assertTrue(customerService.findAll().isEmpty());
    }

    @Test
    void findById_withNonExistentId_shouldReturnNull() {
        assertNull(customerService.findById(999));
    }
}
