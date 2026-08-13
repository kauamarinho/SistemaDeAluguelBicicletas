package org.example.repository;

import org.example.domain.model.Locacao;
import java.util.List;
import java.util.Optional;

public interface LocacaoRepository {

    void salvar(Locacao locacao);

    List<Locacao> listarTodas();

    Optional<Locacao> buscarPorId(int id);
}
