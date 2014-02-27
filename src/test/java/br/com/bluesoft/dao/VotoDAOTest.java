package br.com.bluesoft.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.bluesoft.modelo.Filme;
import br.com.bluesoft.modelo.Usuario;
import br.com.bluesoft.modelo.Voto;

public class VotoDAOTest {

    EntityManager entityManager;

    @Before
    public void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("vote-no-filme");
        this.entityManager = factory.createEntityManager();
    }

    @After
    public void destroy() {
        this.entityManager.close();
    }

    @Test
    public void deveInserirCorretamente() {
        VotoDAO votoDAO = new VotoDAO(this.entityManager);
        Usuario usuario = new Usuario("Stephen", "a@a.com");
        Filme filme = new Filme("Harry Potter");
        Voto voto = new Voto(usuario, filme);
        votoDAO.adiciona(voto);
        assertTrue(votoDAO.existe(voto));
    }

    @Test
    public void deveListarCorretamente() {
        VotoDAO votoDAO = new VotoDAO(this.entityManager);
        Usuario stephen = new Usuario("Stephen", "stephen@a.com");
        Usuario fulano = new Usuario("Fulano", "fulano@a.com");
        Filme harry = new Filme("Harry Potter");
        Filme senhor = new Filme("Senhor Dos Anéis");
        Voto voto1 = new Voto(stephen, harry);
        Voto voto2 = new Voto(stephen, senhor);
        Voto voto3 = new Voto(fulano, senhor);
        votoDAO.adiciona(voto1);
        votoDAO.adiciona(voto2);
        votoDAO.adiciona(voto3);

        List<Voto> votos = votoDAO.listaTodos();
        assertThat(votos.size(), is(equalTo(3)));
        assertTrue(votos.containsAll(Arrays.asList(voto1, voto2, voto3)));
    }

    @Test
    public void deveAgregarCorretamente() {
        VotoDAO votoDAO = new VotoDAO(this.entityManager);
        Usuario stephen = new Usuario("Stephen", "stephen@a.com");
        Usuario fulano = new Usuario("Fulano", "fulano@a.com");
        Filme harry = new Filme("Harry Potter");
        Filme senhor = new Filme("Senhor Dos Anéis");
        Voto voto1 = new Voto(stephen, harry);
        Voto voto2 = new Voto(stephen, senhor);
        Voto voto3 = new Voto(fulano, senhor);
        votoDAO.adiciona(voto1);
        votoDAO.adiciona(voto2);
        votoDAO.adiciona(voto3);

        Map<Filme, Integer> listaVotos = votoDAO.agregarVotos();
        assertThat(listaVotos.get(harry), is(equalTo(1)));
        assertThat(listaVotos.get(senhor), is(equalTo(2)));
    }

}
