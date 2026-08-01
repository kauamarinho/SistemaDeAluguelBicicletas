package org.example.repository;

import org.example.model.Bicicleta;
import java.util.ArrayList;
import java.util.List;

public class BicicletaRepository {

    private List<Bicicleta> bicicletas = new ArrayList<>();

    public void salvar(Bicicleta bicicleta) {
        bicicletas.add(bicicleta);
    }

    public List<Bicicleta> listarTodas() {
        return bicicletas;
    }

    public Bicicleta buscarPorId(int id) {
        for (Bicicleta b : bicicletas) {
            if (b.getId() == id) return b;
        }
        return null;
    }
}