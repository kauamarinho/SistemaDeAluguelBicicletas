package org.example.application;

import org.example.config.AppConfig;
import org.example.domain.exception.AluguelException;
import org.example.domain.enums.FormaPagamento;
import org.example.domain.model.Administrador;
import org.example.domain.model.Bicicleta;
import org.example.domain.model.Cliente;
import org.example.domain.model.Funcionario;
import org.example.domain.model.Locacao;
import org.example.domain.model.Pagamento;
import org.example.domain.model.Reserva;
import org.example.service.BicicletaService;
import org.example.service.ClienteService;
import org.example.service.LocacaoService;
import org.example.service.PagamentoService;
import org.example.service.ReservaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Interface de console do sistema: le a entrada do usuario, chama os services
 * e exibe o resultado. Nao contem regra de negocio - apenas orquestra a
 * interacao via terminal.
 */
public class ConsoleMenu {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Scanner scanner = new Scanner(System.in);

    private final Funcionario funcionario;
    private final Administrador administrador;

    private final BicicletaService bicicletaService;
    private final ClienteService clienteService;
    private final ReservaService reservaService;
    private final LocacaoService locacaoService;
    private final PagamentoService pagamentoService;

    public ConsoleMenu(AppConfig config) {
        this.funcionario = config.getFuncionario();
        this.administrador = config.getAdministrador();
        this.bicicletaService = config.getBicicletaService();
        this.clienteService = config.getClienteService();
        this.reservaService = config.getReservaService();
        this.locacaoService = config.getLocacaoService();
        this.pagamentoService = config.getPagamentoService();
    }

    public void executar() {

        carregarDadosIniciais();

        int opcao;
        do {
            mostrarMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> listarBicicletas();
                case 4 -> realizarReserva();
                case 5 -> cancelarReserva();
                case 6 -> alugarBicicleta();
                case 7 -> devolverBicicleta();
                case 8 -> efetuarPagamento();
                case 9 -> listarLocacoes();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE ALUGUEL DE BICICLETAS ===");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Listar bicicletas");
        System.out.println("4 - Realizar reserva");
        System.out.println("5 - Cancelar reserva");
        System.out.println("6 - Alugar bicicleta");
        System.out.println("7 - Devolver bicicleta");
        System.out.println("8 - Efetuar pagamento");
        System.out.println("9 - Listar locacoes");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private void cadastrarCliente() {

        System.out.println("\n-- Cadastrar Cliente --");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        try {

            Cliente cliente =
                    clienteService.cadastrarCliente(
                            nome,
                            cpf,
                            email
                    );

            System.out.println(
                    "Cliente cadastrado com sucesso! ID: "
                            + cliente.getId()
            );

        } catch (Exception e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }
    }

    private void listarClientes() {
        System.out.println("\n-- Clientes Cadastrados --");
        List<Cliente> clientes = clienteService.listarTodos();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(c.exibirDados());
        }
    }

    private void listarBicicletas() {
        System.out.println("\n-- Bicicletas --");
        for (Bicicleta b : bicicletaService.listarTodas()) {
            System.out.println(b.exibirDados());
        }
    }

    private void realizarReserva() {
        System.out.println("\n-- Realizar Reserva --");

        listarClientes();
        System.out.print("ID do cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = clienteService.buscarPorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        listarBicicletas();
        System.out.print("ID da bicicleta: ");
        int idBike = scanner.nextInt();
        scanner.nextLine();

        Bicicleta bicicleta = bicicletaService.buscarPorId(idBike);
        if (bicicleta == null) {
            System.out.println("Bicicleta nao encontrada.");
            return;
        }

        System.out.print("Data da reserva (dd/mm/aaaa): ");
        String dataTexto = scanner.nextLine();

        try {
            LocalDate data = LocalDate.parse(dataTexto, FORMATO_DATA);
            Reserva reserva = reservaService.realizarReserva(cliente, bicicleta, data);
            System.out.println("Reserva realizada com sucesso! ID: " + reserva.getId());
        } catch (DateTimeParseException e) {
            System.out.println("Erro: data em formato invalido. Use dd/mm/aaaa.");
        } catch (AluguelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cancelarReserva() {
        System.out.println("\n-- Cancelar Reserva --");
        List<Reserva> reservas = reservaService.listarTodas();
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva registrada.");
            return;
        }
        for (Reserva r : reservas) {
            System.out.println(r.exibirDados());
        }

        System.out.print("ID da reserva a cancelar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            reservaService.cancelarReserva(id);
            System.out.println("Reserva cancelada com sucesso.");
        } catch (AluguelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void alugarBicicleta() {
        System.out.println("\n-- Alugar Bicicleta --");

        listarClientes();
        System.out.print("ID do cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = clienteService.buscarPorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        listarBicicletas();
        System.out.print("ID da bicicleta: ");
        int idBike = scanner.nextInt();
        scanner.nextLine();

        Bicicleta bicicleta = bicicletaService.buscarPorId(idBike);
        if (bicicleta == null) {
            System.out.println("Bicicleta nao encontrada.");
            return;
        }

        System.out.print("Data de retirada (dd/mm/aaaa): ");
        String dataTexto = scanner.nextLine();

        try {
            LocalDate data = LocalDate.parse(dataTexto, FORMATO_DATA);
            Locacao locacao = locacaoService.alugarBicicleta(cliente, bicicleta, data);
            System.out.println("Locacao criada com sucesso! ID: " + locacao.getId());
        } catch (DateTimeParseException e) {
            System.out.println("Erro: data em formato invalido. Use dd/mm/aaaa.");
        } catch (AluguelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void devolverBicicleta() {
        System.out.println("\n-- Devolver Bicicleta --");
        List<Locacao> locacoes = locacaoService.listarTodas();
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locacao registrada.");
            return;
        }
        for (Locacao l : locacoes) {
            System.out.println(l.exibirDados());
        }

        System.out.print("ID da locacao: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Locacao locacao = locacaoService.buscarPorId(id);
        if (locacao == null) {
            System.out.println("Locacao nao encontrada.");
            return;
        }

        System.out.print("Data de devolucao (dd/mm/aaaa): ");
        String dataTexto = scanner.nextLine();

        System.out.print("Horas utilizadas: ");
        int horas = scanner.nextInt();
        scanner.nextLine();

        try {
            LocalDate data = LocalDate.parse(dataTexto, FORMATO_DATA);
            locacaoService.devolverBicicleta(locacao, data, horas);
            System.out.println("Bicicleta devolvida com sucesso!");
            System.out.println("Valor total: R$ " + locacao.getValorTotal());
        } catch (DateTimeParseException e) {
            System.out.println("Erro: data em formato invalido. Use dd/mm/aaaa.");
        } catch (AluguelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void efetuarPagamento() {
        System.out.println("\n-- Efetuar Pagamento --");
        List<Locacao> locacoes = locacaoService.listarTodas();
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locacao registrada.");
            return;
        }
        for (Locacao l : locacoes) {
            System.out.println(l.exibirDados());
        }

        System.out.print("ID da locacao: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Locacao locacao = locacaoService.buscarPorId(id);
        if (locacao == null) {
            System.out.println("Locacao nao encontrada.");
            return;
        }

        System.out.print("Forma de pagamento (PIX / DINHEIRO / CARTAO_CREDITO / CARTAO_DEBITO): ");
        String formaTexto = scanner.nextLine();

        try {
            FormaPagamento forma = FormaPagamento.valueOf(formaTexto.trim().toUpperCase().replace(" ", "_"));
            Pagamento pagamento = pagamentoService.efetuarPagamento(locacao, forma);
            System.out.println(pagamento.gerarComprovante());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: forma de pagamento invalida.");
        } catch (AluguelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarLocacoes() {
        System.out.println("\n-- Locacoes Registradas --");
        List<Locacao> locacoes = locacaoService.listarTodas();
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locacao registrada.");
            return;
        }
        for (Locacao l : locacoes) {
            System.out.println(l.exibirDados());
        }
    }

    private void carregarDadosIniciais() {
        bicicletaService.cadastrarBicicleta("Caloi Elite",   15.0);
        bicicletaService.cadastrarBicicleta("Monark Urbana", 12.0);
        bicicletaService.cadastrarBicicleta("Sense Bike",    18.0);
        bicicletaService.cadastrarBicicleta("Caloi Speed",   20.0);
        bicicletaService.cadastrarBicicleta("Houston Bike",  10.0);
    }
}
