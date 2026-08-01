package org.example.model;

public class Reserva {

    private int id;
    private Cliente cliente;
    private Bicicleta bicicleta;
    private String dataReserva;
    private String status;

    public Reserva(int id, Cliente cliente, Bicicleta bicicleta, String dataReserva) {
        this.id = id;
        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.dataReserva = dataReserva;
        this.status = "Ativa";
        this.bicicleta.alterarStatus("Reservada");
    }

    public void cancelarReserva() {
        this.status = "Cancelada";
        this.bicicleta.alterarStatus("Disponivel");
    }

    public String exibirDados() {
        return "Reserva ID: " + id
                + " | Cliente: " + cliente.getNome()
                + " | Bicicleta: " + bicicleta.getModelo()
                + " | Data: " + dataReserva
                + " | Status: " + status;
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Bicicleta getBicicleta() { return bicicleta; }
    public String getStatus() { return status; }
}