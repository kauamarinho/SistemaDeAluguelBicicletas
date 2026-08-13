package org.example.domain.model;

import org.example.domain.enums.StatusBicicleta;

public class Bicicleta implements Cadastravel {

    private int id;
    private String modelo;
    private StatusBicicleta status;
    private double precoHora;

    public Bicicleta(int id, String modelo, StatusBicicleta status, double precoHora) {
        this.id = id;
        this.modelo = modelo;
        this.status = status;
        this.precoHora = precoHora;
    }

    public boolean verificarDisponibilidade() {
        return status == StatusBicicleta.DISPONIVEL;
    }

    public void alterarStatus(StatusBicicleta status) {
        this.status = status;
    }

    public void alterarPrecoHora(double novoPreco) {
        this.precoHora = novoPreco;
    }

    @Override
    public String exibirDados() {
        return "ID: " + id + " | Modelo: " + modelo + " | Status: " + status + " | Preco/Hora: R$ " + precoHora;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNome() { return modelo; }

    public String getModelo() { return modelo; }
    public StatusBicicleta getStatus() { return status; }
    public double getPrecoHora() { return precoHora; }
}
