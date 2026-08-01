package org.example.service;

import org.example.exception.AluguelException;
import org.example.model.Locacao;
import org.example.model.Pagamento;

public class PagamentoService {

    private int proximoId = 1;

    public Pagamento efetuarPagamento(Locacao locacao, String formaPagamento) {
        if (!locacao.getStatus().equals("Finalizada")) {
            throw new AluguelException("A locacao precisa estar finalizada para efetuar pagamento.");
        }
        Pagamento pagamento = new Pagamento(proximoId++, locacao, formaPagamento);
        return pagamento;
    }
}