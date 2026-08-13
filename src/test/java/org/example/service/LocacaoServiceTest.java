package org.example.service;

import org.example.domain.exception.AluguelException;
import org.example.domain.model.*;
import org.example.domain.vo.*;
import org.example.domain.enums.*;
import org.example.repository.inmemory.InMemoryLocacaoRepository;
import org.example.repository.LocacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocacaoServiceTest {

    private LocacaoService locacaoService;
    private Cliente cliente;
    private Bicicleta bicicleta;

    @BeforeEach
    void setUp() {
        LocacaoRepository locacaoRepository = new InMemoryLocacaoRepository();
        locacaoService = new LocacaoService(locacaoRepository);
        cliente = new Cliente(1, "Ana", new Cpf("12345678901"), new Email("ana@email.com"));
        bicicleta = new Bicicleta(1, "Aro 29", StatusBicicleta.DISPONIVEL, 5.0);
    }

    @Test
    void alugarBicicleta_comBicicletaDisponivel_deveCriarLocacaoEAlterarStatusDaBicicleta() {
        Locacao locacao = locacaoService.alugarBicicleta(cliente, bicicleta, LocalDate.of(2026, 8, 12));

        assertEquals(StatusLocacao.EM_ANDAMENTO, locacao.getStatus());
        assertEquals(StatusBicicleta.ALUGADA, bicicleta.getStatus());
        assertEquals(1, locacaoService.listarTodas().size());
    }

    @Test
    void alugarBicicleta_comBicicletaJaAlugada_deveLancarAluguelException() {
        bicicleta.alterarStatus(StatusBicicleta.ALUGADA);

        assertThrows(AluguelException.class,
                () -> locacaoService.alugarBicicleta(cliente, bicicleta, LocalDate.of(2026, 8, 12)));
    }

    @Test
    void alugarBicicleta_comBicicletaRemovida_deveLancarAluguelException() {
        bicicleta.alterarStatus(StatusBicicleta.REMOVIDA);

        assertThrows(AluguelException.class,
                () -> locacaoService.alugarBicicleta(cliente, bicicleta, LocalDate.of(2026, 8, 12)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    void devolverBicicleta_comHorasMenoresOuIguaisAZero_deveLancarAluguelException(int horas) {
        Locacao locacao = locacaoService.alugarBicicleta(cliente, bicicleta, LocalDate.of(2026, 8, 12));

        assertThrows(AluguelException.class,
                () -> locacaoService.devolverBicicleta(locacao, LocalDate.of(2026, 8, 12), horas));
    }

    @Test
    void devolverBicicleta_comLocacaoJaFinalizada_deveLancarAluguelException() {
        Locacao locacao = locacaoService.alugarBicicleta(cliente, bicicleta, LocalDate.of(2026, 8, 12));
        locacaoService.devolverBicicleta(locacao, LocalDate.of(2026, 8, 12), 3);

        assertThrows(AluguelException.class,
                () -> locacaoService.devolverBicicleta(locacao, LocalDate.of(2026, 8, 13), 2));
    }

    @Test
    void devolverBicicleta_valida_deveCalcularValorTotalEDisponibilizarBicicleta() {
        Locacao locacao = locacaoService.alugarBicicleta(cliente, bicicleta, LocalDate.of(2026, 8, 12));

        locacaoService.devolverBicicleta(locacao, LocalDate.of(2026, 8, 12), 4);

        assertEquals(20.0, locacao.getValorTotal());
        assertEquals(StatusLocacao.FINALIZADA, locacao.getStatus());
        assertEquals(StatusBicicleta.DISPONIVEL, bicicleta.getStatus());
    }
}
