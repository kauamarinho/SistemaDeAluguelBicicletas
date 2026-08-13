package org.example.repository;

import org.example.domain.model.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository {

    void salvar(Reserva reserva);

    List<Reserva> listarTodas();

    Optional<Reserva> buscarPorId(int id);
}
