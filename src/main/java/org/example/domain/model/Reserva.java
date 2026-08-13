package org.example.domain.model;

import org.example.domain.enums.StatusBicicleta;
import org.example.domain.enums.StatusReserva;

import java.time.LocalDate;

public class Reserva {

    private int id;
    private Cliente cliente;
    private Bicicleta bicicleta;
    private LocalDate dataReserva;
    private StatusReserva status;

    public Reserva(int id, Cliente cliente, Bicicleta bicicleta, LocalDate dataReserva) {
        this.id = id;
        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.dataReserva = dataReserva;
        this.status = StatusReserva.ATIVA;
        this.bicicleta.alterarStatus(StatusBicicleta.RESERVADA);
    }

    public void cancelarReserva() {
        this.status = StatusReserva.CANCELADA;
        this.bicicleta.alterarStatus(StatusBicicleta.DISPONIVEL);
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
    public LocalDate getDataReserva() { return dataReserva; }
    public StatusReserva getStatus() { return status; }
}
