package org.example.domain.model;

public class Funcionario implements Cadastravel {

    private int id;
    private String nome;

    public Funcionario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String registrarLocacao() {
        return "Locacao registrada pelo funcionario " + nome + ".";
    }

    public String registrarDevolucao() {
        return "Devolucao registrada pelo funcionario " + nome + ".";
    }

    public String consultarLocacao() {
        return "Consulta de locacoes realizada pelo funcionario " + nome + ".";
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
