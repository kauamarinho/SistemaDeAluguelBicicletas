package org.example.service;

import org.example.exception.AluguelException;
import org.example.model.Bicicleta;
import org.example.model.Cliente;
import org.example.model.Locacao;
import org.example.repository.LocacaoRepository;

import java.util.List;

public class LocacaoService {

    private LocacaoRepository locacaoRepository;
    private int proximoId = 1;

    public LocacaoService(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    public Locacao alugarBicicleta(Cliente cliente, Bicicleta bicicleta, String dataRetirada) {

        if (bicicleta.getStatus().equals("Alugada")) {
            throw new AluguelException("Bicicleta ja esta alugada.");
        }

        if (bicicleta.getStatus().equals("Removida")) {
            throw new AluguelException("Bicicleta foi removida do sistema.");
        }

        Locacao locacao = new Locacao(
                proximoId++,
                cliente,
                bicicleta,
                dataRetirada
        );

        locacaoRepository.salvar(locacao);

        return locacao;
    }

    public void devolverBicicleta(Locacao locacao, String dataDevolucao, int horasUsadas) {

        if (!locacao.getStatus().equals("Em andamento")) {
            throw new AluguelException("Essa locacao nao esta em andamento.");
        }

        if (horasUsadas <= 0) {
            throw new AluguelException("As horas usadas devem ser maiores que zero.");
        }

        locacao.finalizarLocacao(dataDevolucao, horasUsadas);
    }

    public Locacao buscarPorId(int id) {
        return locacaoRepository.buscarPorId(id);
    }

    public List<Locacao> listarTodas() {
        return locacaoRepository.listarTodas();
    }
}