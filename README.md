# Sistema de Aluguel de Bicicletas

Aplicação de console em Java para gerenciar aluguel de bicicletas, com cadastro de clientes, reservas, locações, devoluções e pagamentos.

O projeto foi organizado para separar melhor a interface de console, o domínio e a infraestrutura em memória. Ele também já conta com testes automatizados dos services.

## Funcionalidades

- Cadastro de clientes com validação de CPF e e-mail
- Listagem de clientes e bicicletas
- Reserva e cancelamento de reservas
- Aluguel e devolução de bicicletas com cálculo de valor por hora
- Pagamento de locações com geração de comprovante
- Controle automático de status da bicicleta
- Menu interativo via terminal

## Estrutura do projeto

```text
src/main/java/org/example/
├── application/
│   ├── Main.java
│   └── ConsoleMenu.java
├── config/
│   └── AppConfig.java
├── domain/
│   ├── model/
│   │   ├── Administrador.java
│   │   ├── Bicicleta.java
│   │   ├── Cadastravel.java
│   │   ├── Cliente.java
│   │   ├── Funcionario.java
│   │   ├── Locacao.java
│   │   ├── Pagamento.java
│   │   └── Reserva.java
│   ├── vo/
│   │   ├── Cpf.java
│   │   └── Email.java
│   ├── enums/
│   │   ├── FormaPagamento.java
│   │   ├── StatusBicicleta.java
│   │   ├── StatusLocacao.java
│   │   └── StatusReserva.java
│   └── exception/
│       ├── AluguelException.java
│       ├── CpfInvalidoException.java
│       └── EmailInvalidoException.java
├── repository/
│   ├── BicicletaRepository.java
│   ├── ClienteRepository.java
│   ├── LocacaoRepository.java
│   ├── ReservaRepository.java
│   └── inmemory/
│       ├── InMemoryBicicletaRepository.java
│       ├── InMemoryClienteRepository.java
│       ├── InMemoryLocacaoRepository.java
│       └── InMemoryReservaRepository.java
└── service/
    ├── BicicletaService.java
    ├── ClienteService.java
    ├── LocacaoService.java
    ├── PagamentoService.java
    └── ReservaService.java

src/test/java/org/example/service/
├── ClienteServiceTest.java
├── LocacaoServiceTest.java
├── PagamentoServiceTest.java
└── ReservaServiceTest.java
```

## Tecnologias

- Java 25
- Maven
- JUnit 5

## Como executar

### Pré-requisitos

- JDK 25 instalado
- Maven instalado ou Maven Wrapper configurado

### Rodar os testes

```powershell
mvn test
```

Ou, se estiver usando o caminho direto do Maven:

```powershell
& "C:\apache-maven-3.9.16\bin\mvn.cmd" test
```

### Executar a aplicação

```powershell
mvn exec:java -Dexec.mainClass="org.example.application.Main"
```

## Como usar

Ao iniciar, o sistema carrega algumas bicicletas de exemplo e mostra o menu principal.

Fluxo típico:

1. Cadastre um cliente
2. Faça uma reserva ou locação
3. Informe a devolução
4. Efetue o pagamento

## Regras de negócio

- CPF deve conter 11 dígitos numéricos, ignorando pontos e traços
- E-mail deve ser válido
- Só é possível reservar bicicletas disponíveis
- Só é possível alugar bicicletas que não estejam alugadas ou removidas
- A devolução exige horas maiores que zero
- O pagamento só é permitido para locações finalizadas

## Observações de arquitetura

- `Cpf` e `Email` foram modelados como Value Objects
- Os status foram transformados em `enum`
- Os repositórios possuem interfaces e implementações em memória
- O `Main` apenas inicializa a aplicação e delega a execução do console
- A estrutura já está preparada para uma futura migração para Spring Boot e JPA

## Autor

Kauã Marinho

