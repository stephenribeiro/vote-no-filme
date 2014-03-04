package br.com.bluesoft.modelo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import br.com.bluesoft.dao.VotoDAO;

public class ComputadorDeVotosTest {

    @Test
    public void devolveRankCorretamente() {
        Usuario stephen = new Usuario("Stephen", "a@a.com");
        Usuario fulano = new Usuario("Fulano", "a@a.com");

        Filme harry = new Filme("Harry Potter");
        Filme senhor = new Filme("Senhor dos Anéis");
        VotoDAO votoDAO = mock(VotoDAO.class);

        Voto voto1 = new Voto(stephen, harry);
        Voto voto2 = new Voto(stephen, senhor);
        Voto voto3 = new Voto(fulano, senhor);

        List<Voto> votos = Arrays.asList(voto1, voto2, voto3);

        when(votoDAO.listaTodos()).thenReturn(votos);

        ComputadorDeVotos computadorDeVotos = new ComputadorDeVotos();

        TreeMap<Filme, Integer> rankMaisVotados = computadorDeVotos.getRankMaisVotados(votoDAO.listaTodos(), Arrays.asList(harry, senhor));

        assertThat(rankMaisVotados.size(), is(equalTo(2)));

        Integer previous = null;
        for (Map.Entry<Filme, Integer> entry : rankMaisVotados.entrySet()) {
            assertThat(entry.getValue(), is(notNullValue()));
            if (previous != null) {
                assertTrue(entry.getValue() <= previous);
            }
            previous = entry.getValue();
        }
    }

}
