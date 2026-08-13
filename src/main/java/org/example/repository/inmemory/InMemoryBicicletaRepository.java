package org.example.repository.inmemory;

import org.example.domain.model.Bicicleta;
import org.example.repository.BicicletaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBicicletaRepository implements BicicletaRepository {

    private List<Bicicleta> bicicletas = new ArrayList<>();

    @Override
    public void salvar(Bicicleta bicicleta) {
        bicicletas.add(bicicleta);
    }

    @Override
    public List<Bicicleta> listarTodas() {
        return bicicletas;
    }

    @Override
    public Optional<Bicicleta> buscarPorId(int id) {
        for (Bicicleta b : bicicletas) {
            if (b.getId() == id) return Optional.of(b);
        }
        return Optional.empty();
    }
}
