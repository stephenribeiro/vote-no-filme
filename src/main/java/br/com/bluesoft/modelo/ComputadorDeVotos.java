package br.com.bluesoft.modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.bluesoft.dao.VotoDAO;

public class ComputadorDeVotos {

    private VotoDAO votoDAO;

    public ComputadorDeVotos(VotoDAO votoDAO) {
        this.votoDAO = votoDAO;
    }

    public Filme getMaisVotado() {
        Map<Filme, Integer> votos = this.votoDAO.agregarVotos();

        Filme filmeComMaisVotos = null;
        for (Filme filme : votos.keySet()) {
            Integer numeroDeVotosMaisVotado = votos.get(filmeComMaisVotos) == null ? 0 : votos.get(filmeComMaisVotos);
            if (votos.get(filme) > numeroDeVotosMaisVotado) {
                filmeComMaisVotos = filme;
            }
        }

        return filmeComMaisVotos;
    }

    public TreeMap<Filme, Integer> getRankMaisVotados() {
        Map<Filme, Integer> map = this.agregarVotos(this.votoDAO.listaTodos());
        ValueComparator bvc = new ValueComparator(map);
        TreeMap<Filme, Integer> sorted_map = new TreeMap<Filme, Integer>(bvc);
        sorted_map.putAll(map);
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

    class ValueComparator implements Comparator<Filme> {

        Map<Filme, Integer> base;

        public ValueComparator(Map<Filme, Integer> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.
        public int compare(Filme a, Filme b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }

}
