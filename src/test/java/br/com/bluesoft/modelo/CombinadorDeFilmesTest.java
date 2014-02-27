package br.com.bluesoft.modelo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CombinadorDeFilmesTest {

    @Test
    public void naoDeveGerarCombinacoesRepetidas() {
        Filme filmeA = new Filme("A");
        Filme filmeB = new Filme("B");
        Filme filmeC = new Filme("C");
        Filme filmeD = new Filme("D");
        Filme filmeE = new Filme("E");

        CombinadorDeFilmes combinador = new CombinadorDeFilmes();

        List<CombinacaoFilme> combinacoes = combinador.combinar(Arrays.asList(filmeA, filmeB, filmeC, filmeD, filmeE), 2);

        for (CombinacaoFilme combinacao : combinacoes) {
            int igual = 0;
            for (CombinacaoFilme comb : combinacoes) {
                if (combinacao.equals(comb)) {
                    igual++;
                }
            }
            assertThat(igual, is(equalTo(1)));
        }
    }

    @Test
    public void deveGerarCombinacoesCorretamente() {
        Filme filmeA = new Filme("A");
        Filme filmeB = new Filme("B");
        Filme filmeC = new Filme("C");
        Filme filmeD = new Filme("D");
        Filme filmeE = new Filme("E");

        CombinadorDeFilmes combinador = new CombinadorDeFilmes();

        List<CombinacaoFilme> combinacoes = combinador.combinar(Arrays.asList(filmeA, filmeB, filmeC, filmeD, filmeE), 2);

        assertThat(combinacoes.size(), is(equalTo(10))); // de acordo com a formula de combinações: C = N! / (P! / (N-P)!)
        assertThat(combinacoes.get(0), is(equalTo(new CombinacaoFilme(filmeA, filmeB))));
        assertThat(combinacoes.get(3), is(equalTo(new CombinacaoFilme(filmeA, filmeE))));
        assertThat(combinacoes.get(4), is(equalTo(new CombinacaoFilme(filmeB, filmeC))));
        assertThat(combinacoes.get(9), is(equalTo(new CombinacaoFilme(filmeD, filmeE))));
    }

}
