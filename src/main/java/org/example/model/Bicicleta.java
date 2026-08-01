package org.example.model;

public class Bicicleta implements Cadastravel {

    private int id;
    private String modelo;
    private String status;
    private double precoHora;

    public Bicicleta(int id, String modelo, String status, double precoHora) {
        this.id = id;
        this.modelo = modelo;
        this.status = status;
        this.precoHora = precoHora;
    }

    public boolean verificarDisponibilidade() {
        return status.equals("Disponivel");
    }

    public void alterarStatus(String status) {
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
    public String getStatus() { return status; }
    public double getPrecoHora() { return precoHora; }
}