# 🚲 Sistema de Aluguel de Bicicletas

Aplicação de console em **Java** para gerenciar o aluguel de bicicletas: cadastro de clientes, reservas, locações, devoluções e pagamentos. O projeto foi construído com foco em **Programação Orientada a Objetos** e organizado em uma arquitetura em camadas (model, repository e service).

## ✨ Funcionalidades

- Cadastro de clientes com validação de CPF e e-mail  
- Listagem de clientes e de bicicletas  
- Reserva de bicicletas e cancelamento de reservas  
- Aluguel (locação) e devolução de bicicletas com cálculo de valor por hora  
- Pagamento de locações (Dinheiro, Cartão ou Pix) com geração de comprovante  
- Controle automático de status da bicicleta (Disponível, Reservada, Alugada, Removida)  
- Menu interativo via terminal

## 🛠️ Tecnologias

- Java 25  
- Maven  
- JUnit 5 (dependência de teste)

## ▶️ Como executar

Pré-requisitos: **JDK 25** e **Maven** instalados.

Clone o repositório:

git clone https://github.com/\<seu-usuario\>/SistemaDeAluguelBicicletas.git

cd SistemaDeAluguelBicicletas

Compile o projeto:

mvn compile

Execute a aplicação:

mvn exec:java \-Dexec.mainClass="org.example.Main"

## 📋 Usando o sistema

Ao iniciar, o sistema carrega algumas bicicletas de exemplo e exibe o menu:

\=== SISTEMA DE ALUGUEL DE BICICLETAS \===

1 \- Cadastrar cliente

2 \- Listar clientes

3 \- Listar bicicletas

4 \- Realizar reserva

5 \- Cancelar reserva

6 \- Alugar bicicleta

7 \- Devolver bicicleta

8 \- Efetuar pagamento

9 \- Listar locacoes

0 \- Sair

Fluxo típico: cadastre um cliente → alugue uma bicicleta → devolva informando as horas usadas → efetue o pagamento e receba o comprovante.

## ✅ Regras de negócio

- **CPF** deve conter 11 dígitos numéricos (pontos e traços são ignorados).  
- **E-mail** deve conter `@`.  
- Só é possível alugar bicicletas que não estejam **Alugadas** ou **Removidas**.  
- Só é possível reservar bicicletas **Disponíveis**.  
- A devolução exige um número de horas maior que zero; o valor é calculado como `horas × preço/hora`.  
- O pagamento só é permitido para locações com status **Finalizada**.

