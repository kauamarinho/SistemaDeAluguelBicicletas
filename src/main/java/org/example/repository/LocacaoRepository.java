package org.example.repository;

import org.example.model.Locacao;
import java.util.ArrayList;
import java.util.List;

public class LocacaoRepository {

    private List<Locacao> locacoes = new ArrayList<>();

    public void salvar(Locacao locacao) {
        locacoes.add(locacao);
    }

    public List<Locacao> listarTodas() {
        return locacoes;
    }

    public Locacao buscarPorId(int id) {
        for (Locacao l : locacoes) {
            if (l.getId() == id) return l;
        }
        return null;
    }
}