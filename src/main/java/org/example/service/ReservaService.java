package org.example.service;

import org.example.domain.exception.AluguelException;
import org.example.domain.model.Bicicleta;
import org.example.domain.model.Cliente;
import org.example.domain.model.Reserva;
import org.example.repository.ReservaRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservaService {

    private ReservaRepository reservaRepository;
    private int proximoId = 1;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva realizarReserva(Cliente cliente, Bicicleta bicicleta, LocalDate dataReserva) {
        if (!bicicleta.verificarDisponibilidade()) {
            throw new AluguelException("Bicicleta indisponivel para reserva.");
        }
        Reserva reserva = new Reserva(proximoId++, cliente, bicicleta, dataReserva);
        cliente.realizarReserva(reserva);
        reservaRepository.salvar(reserva);
        return reserva;
    }

    public void cancelarReserva(int idReserva) {
        Reserva reserva = reservaRepository.buscarPorId(idReserva)
                .orElseThrow(() -> new AluguelException("Reserva nao encontrada."));
        reserva.cancelarReserva();
    }

    public Reserva buscarPorId(int id) {
        return reservaRepository.buscarPorId(id).orElse(null);
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.listarTodas();
    }
}
