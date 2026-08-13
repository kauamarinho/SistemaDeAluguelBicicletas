package org.example.service;

import org.example.domain.exception.CpfInvalidoException;
import org.example.domain.exception.EmailInvalidoException;
import org.example.domain.model.Cliente;
import org.example.repository.ClienteRepository;
import org.example.repository.inmemory.InMemoryClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        ClienteRepository clienteRepository = new InMemoryClienteRepository();
        clienteService = new ClienteService(clienteRepository);
    }

    @Test
    void cadastrarCliente_comCpfEEmailValidos_devePersistirClienteComIdIncremental() {
        Cliente cliente = clienteService.cadastrarCliente("Ana", "123.456.789-01", "ana@email.com");

        assertEquals(1, cliente.getId());
        assertEquals("Ana", cliente.getNome());
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("ana@email.com", cliente.getEmail());
        assertEquals(1, clienteService.listarTodos().size());
    }

    @Test
    void cadastrarCliente_comCpfFormatado_deveNormalizarPontuacao() {
        Cliente cliente = clienteService.cadastrarCliente("Ana", "123.456.789-01", "ana@email.com");

        assertEquals("12345678901", cliente.getCpf());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123",                  // tamanho invalido
            "1234567890123",        // tamanho invalido
            "abc.def.ghi-01",       // contem letras
            "1234567890a"           // contem letra no meio dos numeros
    })
    void cadastrarCliente_comCpfInvalido_deveLancarCpfInvalidoException(String cpfInvalido) {
        assertThrows(CpfInvalidoException.class,
                () -> clienteService.cadastrarCliente("Ana", cpfInvalido, "ana@email.com"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ana-email.com",  // sem @
            "ana@",           // sem dominio
            "@email.com",     // sem usuario
            "ana@email"       // sem TLD
    })
    void cadastrarCliente_comEmailInvalido_deveLancarEmailInvalidoException(String emailInvalido) {
        assertThrows(EmailInvalidoException.class,
                () -> clienteService.cadastrarCliente("Ana", "12345678901", emailInvalido));
    }

    @Test
    void cadastrarCliente_comCpfInvalido_naoDevePersistirCliente() {
        assertThrows(CpfInvalidoException.class,
                () -> clienteService.cadastrarCliente("Ana", "123", "ana@email.com"));

        assertTrue(clienteService.listarTodos().isEmpty());
    }

    @Test
    void buscarPorId_comIdInexistente_deveRetornarNull() {
        assertNull(clienteService.buscarPorId(999));
    }
}
