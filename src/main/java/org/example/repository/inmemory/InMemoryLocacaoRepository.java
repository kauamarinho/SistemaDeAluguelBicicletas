package org.example.repository.inmemory;

import org.example.domain.model.Locacao;
import org.example.repository.LocacaoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryLocacaoRepository implements LocacaoRepository {

    private List<Locacao> locacoes = new ArrayList<>();

    @Override
    public void salvar(Locacao locacao) {
        locacoes.add(locacao);
    }

    @Override
    public List<Locacao> listarTodas() {
        return locacoes;
    }

    @Override
    public Optional<Locacao> buscarPorId(int id) {
        for (Locacao l : locacoes) {
            if (l.getId() == id) return Optional.of(l);
        }
        return Optional.empty();
    }
}
