package org.example.repository;

import org.example.model.Reserva;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {

    private List<Reserva> reservas = new ArrayList<>();

    public void salvar(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> listarTodas() {
        return reservas;
    }

    public Reserva buscarPorId(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) return r;
        }
        return null;
    }
}