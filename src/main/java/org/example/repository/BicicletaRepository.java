package org.example.repository;

import org.example.domain.model.Bicicleta;
import java.util.List;
import java.util.Optional;

public interface BicicletaRepository {

    void salvar(Bicicleta bicicleta);

    List<Bicicleta> listarTodas();

    Optional<Bicicleta> buscarPorId(int id);
}
