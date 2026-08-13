package org.example.service;

import org.example.domain.exception.AluguelException;
import org.example.domain.model.*;
import org.example.domain.vo.*;
import org.example.domain.enums.*;
import org.example.repository.inmemory.InMemoryReservaRepository;
import org.example.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservaServiceTest {

    private ReservaService reservaService;
    private Cliente cliente;
    private Bicicleta bicicleta;

    @BeforeEach
    void setUp() {
        ReservaRepository reservaRepository = new InMemoryReservaRepository();
        reservaService = new ReservaService(reservaRepository);
        cliente = new Cliente(1, "Ana", new Cpf("12345678901"), new Email("ana@email.com"));
        bicicleta = new Bicicleta(1, "Aro 29", StatusBicicleta.DISPONIVEL, 5.0);
    }

    @Test
    void realizarReserva_comBicicletaDisponivel_deveCriarReservaEMudarStatusDaBicicleta() {
        Reserva reserva = reservaService.realizarReserva(cliente, bicicleta, LocalDate.of(2026, 8, 12));

        assertEquals(StatusBicicleta.RESERVADA, bicicleta.getStatus());
        assertEquals(StatusReserva.ATIVA, reserva.getStatus());
        assertEquals(1, cliente.getReservas().size());
        assertEquals(1, reservaService.listarTodas().size());
    }

    @Test
    void realizarReserva_comBicicletaJaAlugada_deveLancarAluguelException() {
        bicicleta.alterarStatus(StatusBicicleta.ALUGADA);

        assertThrows(AluguelException.class,
                () -> reservaService.realizarReserva(cliente, bicicleta, LocalDate.of(2026, 8, 12)));
    }

    @Test
    void realizarReserva_comBicicletaJaReservada_deveLancarAluguelException() {
        bicicleta.alterarStatus(StatusBicicleta.RESERVADA);

        assertThrows(AluguelException.class,
                () -> reservaService.realizarReserva(cliente, bicicleta, LocalDate.of(2026, 8, 12)));
    }

    @Test
    void realizarReserva_comBicicletaRemovida_deveLancarAluguelException() {
        bicicleta.alterarStatus(StatusBicicleta.REMOVIDA);

        assertThrows(AluguelException.class,
                () -> reservaService.realizarReserva(cliente, bicicleta, LocalDate.of(2026, 8, 12)));
    }

    @Test
    void cancelarReserva_comReservaExistente_deveMudarStatusParaCanceladaELiberarBicicleta() {
        Reserva reserva = reservaService.realizarReserva(cliente, bicicleta, LocalDate.of(2026, 8, 12));

        reservaService.cancelarReserva(reserva.getId());

        assertEquals(StatusReserva.CANCELADA, reserva.getStatus());
        assertEquals(StatusBicicleta.DISPONIVEL, bicicleta.getStatus());
    }

    @Test
    void cancelarReserva_comIdInexistente_deveLancarAluguelException() {
        assertThrows(AluguelException.class, () -> reservaService.cancelarReserva(999));
    }
}
