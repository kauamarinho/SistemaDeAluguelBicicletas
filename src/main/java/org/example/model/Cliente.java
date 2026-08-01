package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Cadastravel {

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private List<Reserva> reservas;

    public Cliente(int id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.reservas = new ArrayList<>();
    }

    public void realizarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String exibirDados() {
        return "ID: " + id
                + " | Nome: " + nome
                + " | CPF: " + cpf
                + " | Email: " + email;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}