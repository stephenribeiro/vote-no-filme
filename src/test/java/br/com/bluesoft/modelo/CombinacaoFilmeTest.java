package br.com.bluesoft.modelo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CombinacaoFilmeTest {

    @Test
    public void verificaEqualsParaCombinacaoIdentica() {
        Filme filmeA = new Filme("A");
        Filme filmeB = new Filme("B");

        CombinacaoFilme combinacao1 = new CombinacaoFilme(filmeA, filmeB);
        CombinacaoFilme combinacao2 = new CombinacaoFilme(filmeA, filmeB);

        assertThat(combinacao1, is(equalTo(combinacao2)));
    }

    @Test
    public void verificaEqualsParaFilmesInvertidos() {
        Filme filmeA = new Filme("A");
        Filme filmeB = new Filme("B");

        CombinacaoFilme combinacao1 = new CombinacaoFilme(filmeA, filmeB);
        CombinacaoFilme combinacao2 = new CombinacaoFilme(filmeB, filmeA);

        assertThat(combinacao1, is(equalTo(combinacao2)));
    }

    @Test
    public void verificaEqualsParaUmFilmeDiferente() {
        Filme filmeA = new Filme("A");
        Filme filmeB = new Filme("B");

        CombinacaoFilme combinacao1 = new CombinacaoFilme(filmeA, filmeB);
        CombinacaoFilme combinacao2 = new CombinacaoFilme(filmeA, filmeA);

        assertThat(combinacao1, is(not(equalTo(combinacao2))));
    }

    @Test
    public void verificaEqualsParaCombinacoesDiferentes() {
        Filme filmeA = new Filme("A");
        Filme filmeB = new Filme("B");
        Filme filmeC = new Filme("C");
        Filme filmeD = new Filme("D");

        CombinacaoFilme combinacao1 = new CombinacaoFilme(filmeA, filmeB);
        CombinacaoFilme combinacao2 = new CombinacaoFilme(filmeC, filmeD);

        assertThat(combinacao1, is(not(equalTo(combinacao2))));
    }

}
