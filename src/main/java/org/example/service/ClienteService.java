package org.example.service;

import org.example.domain.model.Cliente;
import org.example.domain.vo.Cpf;
import org.example.domain.vo.Email;
import org.example.repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private ClienteRepository clienteRepository;
    private int proximoId = 1;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(String nome, String cpf, String email) {

        Cliente cliente = new Cliente(
                proximoId++,
                nome,
                new Cpf(cpf),
                new Email(email)
        );

        clienteRepository.salvar(cliente);

        return cliente;
    }

    public Cliente buscarPorId(int id) {
        return clienteRepository.buscarPorId(id).orElse(null);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }
}
