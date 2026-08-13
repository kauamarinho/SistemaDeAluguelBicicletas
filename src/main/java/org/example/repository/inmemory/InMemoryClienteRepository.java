package org.example.repository.inmemory;

import org.example.domain.model.Cliente;
import org.example.repository.ClienteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryClienteRepository implements ClienteRepository {

    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clientes;
    }

    @Override
    public Optional<Cliente> buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) return Optional.of(c);
        }
        return Optional.empty();
    }
}
