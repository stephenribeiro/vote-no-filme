package br.com.bluesoft.modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

/**
 * Responsável por computar os votos
 * 
 * @author stephen.ribeiro
 */
@Repository
public class ComputadorDeVotos {

    /**
     * Devolve os filmes mais votados em ordem decrescente de acordo com uma lista de votos
     * 
     * @param votos dos filmes
     * @param opcoes possíveis
     * @return os filmes ordenados
     */
    public TreeMap<Filme, Integer> getRankMaisVotados(List<Voto> votos, List<Filme> opcoes) {
        Map<Filme, Integer> votosAgregados = this.agregarVotos(votos);
        this.preencheNaoVotados(votosAgregados, opcoes);
        ValueComparator bvc = new ValueComparator(votosAgregados);
        TreeMap<Filme, Integer> sorted_map = new TreeMap<Filme, Integer>(bvc);
        sorted_map.putAll(votosAgregados);
        return sorted_map;
    }

    /**
     * Agrega os votos por filme
     * 
     * @param votos a serem agregados
     * @return os votos agregados por filme
     */
    private Map<Filme, Integer> agregarVotos(List<Voto> votos) {
        Map<Filme, Integer> votosAgregados = new HashMap<Filme, Integer>();
        for (Voto voto : votos) {
            Integer numeroDeVotos = votosAgregados.get(voto.getFilme()) == null ? 1 : votosAgregados.get(voto.getFilme()) + 1;
            votosAgregados.put(voto.getFilme(), numeroDeVotos);
        }
        return votosAgregados;
    }

    /**
     * Preenche os filmes que não foram votados com zero votos
     * 
     * @param votos lista de votos
     * @param opcoes de filmes para votação
     */
    private void preencheNaoVotados(Map<Filme, Integer> votos, List<Filme> opcoes) {
        for (Filme opcao : opcoes) {
            if (votos.get(opcao) == null) {
                votos.put(opcao, 0);
            }
        }
    }

    /**
     * Responsável pela ordenação dos filmes de acordo com a quantidade de votos
     * 
     * @author stephen.ribeiro
     */
    class ValueComparator implements Comparator<Filme> {

        Map<Filme, Integer> base;

        public ValueComparator(Map<Filme, Integer> base) {
            this.base = base;
        }

        public int compare(Filme a, Filme b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
