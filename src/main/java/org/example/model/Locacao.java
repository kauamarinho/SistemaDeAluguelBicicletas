package org.example.model;

public class Locacao {

    private int id;
    private Cliente cliente;
    private Bicicleta bicicleta;
    private String dataRetirada;
    private String dataDevolucao;
    private int horasUsadas;
    private double valorTotal;
    private String status;

    public Locacao(int id, Cliente cliente, Bicicleta bicicleta, String dataRetirada) {
        this.id = id;
        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.dataRetirada = dataRetirada;
        this.status = "Em andamento";
        this.bicicleta.alterarStatus("Alugada");
    }

    public void finalizarLocacao(String dataDevolucao, int horasUsadas) {
        this.dataDevolucao = dataDevolucao;
        this.horasUsadas = horasUsadas;
        this.valorTotal = horasUsadas * bicicleta.getPrecoHora();
        this.status = "Finalizada";
        this.bicicleta.alterarStatus("Disponivel");
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
    public String getDataRetirada() { return dataRetirada; }
    public String getDataDevolucao() { return dataDevolucao; }
    public double getValorTotal() { return valorTotal; }
    public String getStatus() { return status; }
}