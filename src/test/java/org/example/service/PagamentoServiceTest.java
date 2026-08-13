package org.example.service;

import org.example.domain.exception.AluguelException;
import org.example.domain.model.*;
import org.example.domain.vo.*;
import org.example.domain.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoServiceTest {

    private PagamentoService pagamentoService;
    private Cliente cliente;
    private Bicicleta bicicleta;

    @BeforeEach
    void setUp() {
        pagamentoService = new PagamentoService();
        cliente = new Cliente(1, "Ana", new Cpf("12345678901"), new Email("ana@email.com"));
        bicicleta = new Bicicleta(1, "Aro 29", StatusBicicleta.DISPONIVEL, 5.0);
    }

    @Test
    void efetuarPagamento_comLocacaoEmAndamento_deveLancarAluguelException() {
        Locacao locacao = new Locacao(1, cliente, bicicleta, LocalDate.of(2026, 8, 12));

        assertThrows(AluguelException.class,
                () -> pagamentoService.efetuarPagamento(locacao, FormaPagamento.PIX));
    }

    @Test
    void efetuarPagamento_comLocacaoFinalizada_deveGerarPagamentoConfirmadoComValorDaLocacao() {
        Locacao locacao = new Locacao(1, cliente, bicicleta, LocalDate.of(2026, 8, 12));
        locacao.finalizarLocacao(LocalDate.of(2026, 8, 12), 3);

        Pagamento pagamento = pagamentoService.efetuarPagamento(locacao, FormaPagamento.PIX);

        assertEquals("Confirmado", pagamento.getStatus());
        assertEquals(15.0, pagamento.getValorTotal());
    }

    @Test
    void efetuarPagamento_deveGerarComprovanteComFormaDePagamentoInformada() {
        Locacao locacao = new Locacao(1, cliente, bicicleta, LocalDate.of(2026, 8, 12));
        locacao.finalizarLocacao(LocalDate.of(2026, 8, 12), 2);

        Pagamento pagamento = pagamentoService.efetuarPagamento(locacao, FormaPagamento.CARTAO_CREDITO);

        assertTrue(pagamento.gerarComprovante().contains("CARTAO_CREDITO"));
    }
}
