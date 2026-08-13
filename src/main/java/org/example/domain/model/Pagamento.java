package org.example.domain.model;

import org.example.domain.enums.FormaPagamento;

public class Pagamento {

    private int id;
    private Locacao locacao;
    private double valor;
    private FormaPagamento formaPagamento;
    private String status;

    public Pagamento(int id, Locacao locacao, FormaPagamento formaPagamento) {
        this.id = id;
        this.locacao = locacao;
        this.valor = locacao.getValorTotal();
        this.formaPagamento = formaPagamento;
        this.status = "Confirmado";
    }

    public String gerarComprovante() {
        return "COMPROVANTE"
                + "\nCliente: " + locacao.getCliente().getNome()
                + "\nBicicleta: " + locacao.getBicicleta().getModelo()
                + "\nForma de pagamento: " + formaPagamento
                + "\nValor pago: R$ " + valor
                + "\nStatus: " + status;
    }

    public int getId() {
        return id;
    }

    public double getValorTotal() {
        return valor;
    }

    public String getStatus() {
        return status;
    }
}
