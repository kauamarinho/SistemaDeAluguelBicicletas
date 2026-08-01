package org.example.service;

import org.example.exception.AluguelException;
import org.example.model.Bicicleta;
import org.example.model.Cliente;
import org.example.model.Reserva;
import org.example.repository.ReservaRepository;
import java.util.List;

public class ReservaService {

    private ReservaRepository reservaRepository;
    private int proximoId = 1;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva realizarReserva(Cliente cliente, Bicicleta bicicleta, String dataReserva) {
        if (!bicicleta.verificarDisponibilidade()) {
            throw new AluguelException("Bicicleta indisponivel para reserva.");
        }
        Reserva reserva = new Reserva(proximoId++, cliente, bicicleta, dataReserva);
        cliente.realizarReserva(reserva);
        reservaRepository.salvar(reserva);
        return reserva;
    }

    public void cancelarReserva(int idReserva) {
        Reserva reserva = reservaRepository.buscarPorId(idReserva);
        if (reserva == null) {
            throw new AluguelException("Reserva nao encontrada.");
        }
        reserva.cancelarReserva();
    }

    public Reserva buscarPorId(int id) {
        return reservaRepository.buscarPorId(id);
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.listarTodas();
    }
}