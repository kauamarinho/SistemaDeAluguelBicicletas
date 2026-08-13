package org.example.service;

import org.example.domain.model.Bicicleta;
import org.example.domain.enums.StatusBicicleta;
import org.example.repository.BicicletaRepository;
import java.util.List;

public class BicicletaService {

    private BicicletaRepository bicicletaRepository;
    private int proximoId = 1;

    public BicicletaService(BicicletaRepository bicicletaRepository) {
        this.bicicletaRepository = bicicletaRepository;
    }

    public Bicicleta cadastrarBicicleta(String modelo, double precoHora) {
        Bicicleta bicicleta = new Bicicleta(proximoId++, modelo, StatusBicicleta.DISPONIVEL, precoHora);
        bicicletaRepository.salvar(bicicleta);
        return bicicleta;
    }

    public List<Bicicleta> listarTodas() {
        return bicicletaRepository.listarTodas();
    }

    public Bicicleta buscarPorId(int id) {
        return bicicletaRepository.buscarPorId(id).orElse(null);
    }
}