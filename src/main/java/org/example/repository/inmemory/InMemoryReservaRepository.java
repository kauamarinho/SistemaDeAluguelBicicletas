package org.example.repository.inmemory;

import org.example.domain.model.Reserva;
import org.example.repository.ReservaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryReservaRepository implements ReservaRepository {

    private List<Reserva> reservas = new ArrayList<>();

    @Override
    public void salvar(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public List<Reserva> listarTodas() {
        return reservas;
    }

    @Override
    public Optional<Reserva> buscarPorId(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) return Optional.of(r);
        }
        return Optional.empty();
    }
}
