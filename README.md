# 🚲 Bicycle Rental System

![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven)
![JUnit5](https://img.shields.io/badge/Tests-JUnit5-25A162?logo=junit5)

A Java console application for managing bicycle rentals, including customer registration, reservations, rentals, returns, and payments.

The project is organized to cleanly separate the console interface, the domain, and the in-memory infrastructure, and already includes automated tests for the services.

## ✨ Features

- Customer registration with CPF and email validation
- Listing of customers and bicycles
- Reservation and cancellation of reservations
- Bicycle rental and return with hourly rate calculation
- Rental payment with receipt generation
- Automatic bicycle status control
- Interactive terminal menu

## 📁 Project structure

```
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

## 🛠️ Technologies

- Java 25
- Maven
- JUnit 5

## 🚀 How to run

### Prerequisites

- JDK 25 installed
- Maven installed or Maven Wrapper configured

### Run the tests

```bash
mvn test
```

Or, if using the direct Maven path:

```powershell
& "C:\apache-maven-3.9.16\bin\mvn.cmd" test
```

### Run the application

```bash
mvn exec:java -Dexec.mainClass="org.example.application.Main"
```

## 📖 How to use

On startup, the system loads a few sample bicycles and shows the main menu.

Typical flow:

1. Register a customer
2. Make a reservation or rental
3. Report the return
4. Make the payment

## 📋 Business rules

- CPF must contain 11 numeric digits, ignoring dots and dashes
- Email must be valid
- Only available bicycles can be reserved
- Only bicycles that are not rented or removed can be rented out
- Returns require hours greater than zero
- Payment is only allowed for finalized rentals

## 🏗️ Architecture notes

- `Cpf` and `Email` were modeled as Value Objects
- Statuses were converted into `enum`
- Repositories have interfaces and in-memory implementations
- `Main` only initializes the application and delegates execution to the console
- The structure is already prepared for a future migration to Spring Boot and JPA

## 👤 Author

Kauã Marinho
