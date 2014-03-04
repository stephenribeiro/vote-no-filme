package br.com.bluesoft.modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

@Repository
public class ComputadorDeVotos {

    public TreeMap<Filme, Integer> getRankMaisVotados(List<Voto> votos, List<Filme> opcoes) {
        Map<Filme, Integer> votosAgregados = this.agregarVotos(votos);
        this.preencheNaoVotados(votosAgregados, opcoes);
        ValueComparator bvc = new ValueComparator(votosAgregados);
        TreeMap<Filme, Integer> sorted_map = new TreeMap<Filme, Integer>(bvc);
        sorted_map.putAll(votosAgregados);
        return sorted_map;
    }

    private Map<Filme, Integer> agregarVotos(List<Voto> votos) {
        Map<Filme, Integer> votosAgregados = new HashMap<Filme, Integer>();
        for (Voto voto : votos) {
            Integer numeroDeVotos = votosAgregados.get(voto.getFilme()) == null ? 1 : votosAgregados.get(voto.getFilme()) + 1;
            votosAgregados.put(voto.getFilme(), numeroDeVotos);
        }
        return votosAgregados;
    }

    private void preencheNaoVotados(Map<Filme, Integer> votos, List<Filme> opcoes) {
        for (Filme opcao : opcoes) {
            if (votos.get(opcao) == null) {
                votos.put(opcao, 0);
            }
        }
    }

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
