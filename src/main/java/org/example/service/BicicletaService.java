package org.example.service;

import org.example.model.Bicicleta;
import org.example.repository.BicicletaRepository;
import java.util.List;

public class BicicletaService {

    private BicicletaRepository bicicletaRepository;
    private int proximoId = 1;

    public BicicletaService(BicicletaRepository bicicletaRepository) {
        this.bicicletaRepository = bicicletaRepository;
    }

    public Bicicleta cadastrarBicicleta(String modelo, double precoHora) {
        Bicicleta bicicleta = new Bicicleta(proximoId++, modelo, "Disponivel", precoHora);
        bicicletaRepository.salvar(bicicleta);
        return bicicleta;
    }

    public List<Bicicleta> listarTodas() {
        return bicicletaRepository.listarTodas();
    }

    public Bicicleta buscarPorId(int id) {
        return bicicletaRepository.buscarPorId(id);
    }
}