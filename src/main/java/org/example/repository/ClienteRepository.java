package org.example.repository;

import org.example.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    void salvar(Cliente cliente);

    List<Cliente> listarTodos();

    Optional<Cliente> buscarPorId(int id);
}
