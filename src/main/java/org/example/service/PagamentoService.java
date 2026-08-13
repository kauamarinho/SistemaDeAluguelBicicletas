package org.example.service;

import org.example.domain.exception.AluguelException;
import org.example.domain.enums.FormaPagamento;
import org.example.domain.model.Locacao;
import org.example.domain.model.Pagamento;
import org.example.domain.enums.StatusLocacao;

public class PagamentoService {

    private int proximoId = 1;

    public Pagamento efetuarPagamento(Locacao locacao, FormaPagamento formaPagamento) {
        if (locacao.getStatus() != StatusLocacao.FINALIZADA) {
            throw new AluguelException("A locacao precisa estar finalizada para efetuar pagamento.");
        }
        Pagamento pagamento = new Pagamento(proximoId++, locacao, formaPagamento);
        return pagamento;
    }
}
