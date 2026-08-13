package org.example.domain.model;

import org.example.domain.enums.StatusBicicleta;
import org.example.domain.enums.StatusLocacao;

import java.time.LocalDate;

public class Locacao {

    private int id;
    private Cliente cliente;
    private Bicicleta bicicleta;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private int horasUsadas;
    private double valorTotal;
    private StatusLocacao status;

    public Locacao(int id, Cliente cliente, Bicicleta bicicleta, LocalDate dataRetirada) {
        this.id = id;
        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.dataRetirada = dataRetirada;
        this.status = StatusLocacao.EM_ANDAMENTO;
        this.bicicleta.alterarStatus(StatusBicicleta.ALUGADA);
    }

    public void finalizarLocacao(LocalDate dataDevolucao, int horasUsadas) {
        this.dataDevolucao = dataDevolucao;
        this.horasUsadas = horasUsadas;
        this.valorTotal = horasUsadas * bicicleta.getPrecoHora();
        this.status = StatusLocacao.FINALIZADA;
        this.bicicleta.alterarStatus(StatusBicicleta.DISPONIVEL);
    }

    public String exibirDados() {
        return "Locacao ID: " + id
                + " | Cliente: " + cliente.getNome()
                + " | Bicicleta: " + bicicleta.getModelo()
                + " | Retirada: " + dataRetirada
                + " | Status: " + status;
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Bicicleta getBicicleta() { return bicicleta; }
    public LocalDate getDataRetirada() { return dataRetirada; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public double getValorTotal() { return valorTotal; }
    public StatusLocacao getStatus() { return status; }
}
