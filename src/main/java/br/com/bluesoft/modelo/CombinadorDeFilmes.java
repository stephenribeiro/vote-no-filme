package br.com.bluesoft.modelo;

import java.util.ArrayList;
import java.util.List;

public class CombinadorDeFilmes {

    public List<CombinacaoFilme> combinar(List<Filme> filmes, int escolhas) {
        List<CombinacaoFilme> combinacoes = new ArrayList<CombinacaoFilme>();

        for (int i = 0; i < filmes.size(); i++) {
            for (int j = i + 1; j < filmes.size(); j++) {
                CombinacaoFilme combinacao = new CombinacaoFilme(filmes.get(i), filmes.get(j));
                combinacoes.add(combinacao);
            }
        }
        return combinacoes;
    }

}
