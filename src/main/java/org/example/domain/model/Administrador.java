package org.example.domain.model;

import org.example.domain.enums.StatusBicicleta;

public class Administrador implements Cadastravel {

    private int id;
    private String nome;

    public Administrador(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String alterarPreco(Bicicleta bicicleta, double novoPreco) {
        bicicleta.alterarPrecoHora(novoPreco);

        return "Administrador " + nome +
                " alterou o preco para R$ " + novoPreco + ".";
    }

    public String removerBicicleta(Bicicleta bicicleta) {
        bicicleta.alterarStatus(StatusBicicleta.REMOVIDA);

        return "Administrador " + nome +
                " removeu a bicicleta.";
    }

    public String consultarBicicletas() {
        return "Administrador " + nome +
                " consultou as bicicletas.";
    }

    @Override
    public String exibirDados() {
        return "Admin ID: " + id + " | Nome: " + nome;
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
