package org.example.service;

import org.example.exception.CpfInvalidoException;
import org.example.exception.EmailInvalidoException;
import org.example.model.Cliente;
import org.example.repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private ClienteRepository clienteRepository;
    private int proximoId = 1;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(String nome, String cpf, String email) {

        validarCpf(cpf);
        validarEmail(email);

        Cliente cliente = new Cliente(
                proximoId++,
                nome,
                cpf,
                email
        );

        clienteRepository.salvar(cliente);

        return cliente;
    }

    private void validarCpf(String cpf) {

        cpf = cpf.replace(".", "")
                .replace("-", "");

        if (cpf.length() != 11) {
            throw new CpfInvalidoException(
                    "CPF deve possuir 11 numeros."
            );
        }

        for (int i = 0; i < cpf.length(); i++) {

            if (!Character.isDigit(cpf.charAt(i))) {
                throw new CpfInvalidoException(
                        "CPF deve conter apenas numeros."
                );
            }
        }
    }

    private void validarEmail(String email) {

        if (!email.contains("@")) {
            throw new EmailInvalidoException(
                    "Email deve conter @."
            );
        }
    }

    public Cliente buscarPorId(int id) {
        return clienteRepository.buscarPorId(id);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }
}