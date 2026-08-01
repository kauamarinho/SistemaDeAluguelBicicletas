package org.example;

import org.example.exception.AluguelException;
import org.example.model.*;
import org.example.repository.*;
import org.example.service.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static Funcionario funcionario =
            new Funcionario(1, "Carlos");

    static Administrador administrador =
            new Administrador(2, "Marcos");

    static BicicletaRepository bicicletaRepository = new BicicletaRepository();
    static ClienteRepository   clienteRepository   = new ClienteRepository();
    static ReservaRepository   reservaRepository   = new ReservaRepository();
    static LocacaoRepository   locacaoRepository   = new LocacaoRepository();

    static BicicletaService bicicletaService = new BicicletaService(bicicletaRepository);
    static ClienteService   clienteService   = new ClienteService(clienteRepository);
    static ReservaService   reservaService   = new ReservaService(reservaRepository);
    static LocacaoService   locacaoService   = new LocacaoService(locacaoRepository);
    static PagamentoService pagamentoService = new PagamentoService();

    public static void main(String[] args) {

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

    static void mostrarMenu() {
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

    static void cadastrarCliente() {

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

    static void listarClientes() {
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

    static void listarBicicletas() {
        System.out.println("\n-- Bicicletas --");
        for (Bicicleta b : bicicletaService.listarTodas()) {
            System.out.println(b.exibirDados());
        }
    }

    static void realizarReserva() {
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
        String data = scanner.nextLine();

        try {
            Reserva reserva = reservaService.realizarReserva(cliente, bicicleta, data);
            System.out.println("Reserva realizada com sucesso! ID: " + reserva.getId());
        } catch (AluguelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void cancelarReserva() {
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

    static void alugarBicicleta() {
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
        String data = scanner.nextLine();

        try {
            Locacao locacao = locacaoService.alugarBicicleta(cliente, bicicleta, data);
            System.out.println("Locacao criada com sucesso! ID: " + locacao.getId());
        } catch (AluguelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void devolverBicicleta() {
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
        String data = scanner.nextLine();

        System.out.print("Horas utilizadas: ");
        int horas = scanner.nextInt();
        scanner.nextLine();

        locacaoService.devolverBicicleta(locacao, data, horas);
        System.out.println("Bicicleta devolvida com sucesso!");
        System.out.println("Valor total: R$ " + locacao.getValorTotal());
    }

    static void efetuarPagamento() {
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

        System.out.print("Forma de pagamento (Dinheiro / Cartao / Pix): ");
        String forma = scanner.nextLine();

        try {
            Pagamento pagamento = pagamentoService.efetuarPagamento(locacao, forma);
            System.out.println(pagamento.gerarComprovante());
        } catch (AluguelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void listarLocacoes() {
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

    static void carregarDadosIniciais() {
        bicicletaService.cadastrarBicicleta("Caloi Elite",   15.0);
        bicicletaService.cadastrarBicicleta("Monark Urbana", 12.0);
        bicicletaService.cadastrarBicicleta("Sense Bike",    18.0);
        bicicletaService.cadastrarBicicleta("Caloi Speed",   20.0);
        bicicletaService.cadastrarBicicleta("Houston Bike",  10.0);
    }
}