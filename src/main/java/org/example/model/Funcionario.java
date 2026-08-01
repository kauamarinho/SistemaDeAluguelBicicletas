package org.example.model;

public class Funcionario implements Cadastravel {

    private int id;
    private String nome;

    public Funcionario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void registrarLocacao() {
        System.out.println("Locacao registrada pelo funcionario " + nome + ".");
    }

    public void registrarDevolucao() {
        System.out.println("Devolucao registrada pelo funcionario " + nome + ".");
    }

    public void consultarLocacao() {
        System.out.println("Consulta de locacoes realizada pelo funcionario " + nome + ".");
    }

    @Override
    public String exibirDados() {
        return "Funcionario ID: " + id + " | Nome: " + nome;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }
}