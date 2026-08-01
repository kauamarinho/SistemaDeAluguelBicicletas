package org.example.model;

public class Administrador implements Cadastravel {

    private int id;
    private String nome;

    public Administrador(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void alterarPreco(Bicicleta bicicleta, double novoPreco) {
        bicicleta.alterarPrecoHora(novoPreco);

        System.out.println(
                "Administrador " + nome +
                        " alterou o preco para R$ " + novoPreco + "."
        );
    }

    public void removerBicicleta(Bicicleta bicicleta) {
        bicicleta.alterarStatus("Removida");

        System.out.println(
                "Administrador " + nome +
                        " removeu a bicicleta."
        );
    }

    public void consultarBicicletas() {
        System.out.println(
                "Administrador " + nome +
                        " consultou as bicicletas."
        );
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