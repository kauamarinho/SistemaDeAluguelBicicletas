package org.example.service;

import org.example.domain.exception.AluguelException;
import org.example.domain.model.Bicicleta;
import org.example.domain.model.Cliente;
import org.example.domain.model.Locacao;
import org.example.domain.enums.StatusBicicleta;
import org.example.domain.enums.StatusLocacao;
import org.example.repository.LocacaoRepository;

import java.time.LocalDate;
import java.util.List;

public class LocacaoService {

    private LocacaoRepository locacaoRepository;
    private int proximoId = 1;

    public LocacaoService(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    public Locacao alugarBicicleta(Cliente cliente, Bicicleta bicicleta, LocalDate dataRetirada) {

        if (bicicleta.getStatus() == StatusBicicleta.ALUGADA) {
            throw new AluguelException("Bicicleta ja esta alugada.");
        }

        if (bicicleta.getStatus() == StatusBicicleta.REMOVIDA) {
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

    public void devolverBicicleta(Locacao locacao, LocalDate dataDevolucao, int horasUsadas) {

        if (locacao.getStatus() != StatusLocacao.EM_ANDAMENTO) {
            throw new AluguelException("Essa locacao nao esta em andamento.");
        }

        if (horasUsadas <= 0) {
            throw new AluguelException("As horas usadas devem ser maiores que zero.");
        }

        locacao.finalizarLocacao(dataDevolucao, horasUsadas);
    }

    public Locacao buscarPorId(int id) {
        return locacaoRepository.buscarPorId(id).orElse(null);
    }

    public List<Locacao> listarTodas() {
        return locacaoRepository.listarTodas();
    }
}
