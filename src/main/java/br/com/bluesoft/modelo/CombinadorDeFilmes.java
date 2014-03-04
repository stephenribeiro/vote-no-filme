package br.com.bluesoft.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Responsável por criar combinações de filmes por escolhas para que não haja combinações repetidas
 * 
 * @author stephen.ribeiro
 */
@Repository
public class CombinadorDeFilmes {

    /**
     * Combina os filmes de acordo com a quantidade de escolhas
     * 
     * @param filmes a serem combinados
     * @param escolhas quantidade de escolhas possíveis
     * @return uma lista de combinações de filmes
     */
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
