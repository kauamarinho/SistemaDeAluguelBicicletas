package org.example.config;

import org.example.domain.model.Administrador;
import org.example.domain.model.Funcionario;
import org.example.repository.BicicletaRepository;
import org.example.repository.ClienteRepository;
import org.example.repository.LocacaoRepository;
import org.example.repository.ReservaRepository;
import org.example.repository.inmemory.InMemoryBicicletaRepository;
import org.example.repository.inmemory.InMemoryClienteRepository;
import org.example.repository.inmemory.InMemoryLocacaoRepository;
import org.example.repository.inmemory.InMemoryReservaRepository;
import org.example.service.BicicletaService;
import org.example.service.ClienteService;
import org.example.service.LocacaoService;
import org.example.service.PagamentoService;
import org.example.service.ReservaService;

/**
 * Centraliza a instanciacao e a fiacao (wiring) das dependencias da aplicacao:
 * repositorios, services e os atores fixos do sistema (funcionario/administrador).
 * Hoje monta tudo em memoria; em uma migracao futura para Spring Boot, essas
 * instancias tendem a virar beans geridos pelo container.
 */
public class AppConfig {

    private final Funcionario funcionario;
    private final Administrador administrador;

    private final BicicletaRepository bicicletaRepository;
    private final ClienteRepository clienteRepository;
    private final ReservaRepository reservaRepository;
    private final LocacaoRepository locacaoRepository;

    private final BicicletaService bicicletaService;
    private final ClienteService clienteService;
    private final ReservaService reservaService;
    private final LocacaoService locacaoService;
    private final PagamentoService pagamentoService;

    public AppConfig() {
        this.funcionario = new Funcionario(1, "Carlos");
        this.administrador = new Administrador(2, "Marcos");

        this.bicicletaRepository = new InMemoryBicicletaRepository();
        this.clienteRepository = new InMemoryClienteRepository();
        this.reservaRepository = new InMemoryReservaRepository();
        this.locacaoRepository = new InMemoryLocacaoRepository();

        this.bicicletaService = new BicicletaService(bicicletaRepository);
        this.clienteService = new ClienteService(clienteRepository);
        this.reservaService = new ReservaService(reservaRepository);
        this.locacaoService = new LocacaoService(locacaoRepository);
        this.pagamentoService = new PagamentoService();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public BicicletaService getBicicletaService() {
        return bicicletaService;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public ReservaService getReservaService() {
        return reservaService;
    }

    public LocacaoService getLocacaoService() {
        return locacaoService;
    }

    public PagamentoService getPagamentoService() {
        return pagamentoService;
    }
}
