package br.com.bluesoft.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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

    private EntityManager entityManager;

    private VotoDAO votoDAO;

    @Before
    public void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("vote-no-filme-teste");
        this.entityManager = factory.createEntityManager();
        this.votoDAO = new VotoDAO(this.entityManager);
    }

    @After
    public void destroy() {
        this.entityManager.close();
    }

    @Test
    public void deveInserirCorretamente() {
        Usuario usuario = new Usuario("Stephen", "a@a.com");
        Filme filme = new Filme("Harry Potter");
        Voto voto = new Voto(usuario, filme);
        this.votoDAO.adiciona(voto);
        assertTrue(this.votoDAO.existe(voto));
    }

    @Test
    public void deveListarCorretamente() {
        Usuario stephen = new Usuario("Stephen", "stephen@a.com");
        Usuario fulano = new Usuario("Fulano", "fulano@a.com");
        Filme harry = new Filme("Harry Potter");
        Filme senhor = new Filme("Senhor Dos Anéis");
        Voto voto1 = new Voto(stephen, harry);
        Voto voto2 = new Voto(stephen, senhor);
        Voto voto3 = new Voto(fulano, senhor);
        this.votoDAO.adiciona(voto1);
        this.votoDAO.adiciona(voto2);
        this.votoDAO.adiciona(voto3);

        List<Voto> votos = this.votoDAO.listaTodos();
        assertThat(votos.size(), is(equalTo(3)));
        assertTrue(votos.containsAll(Arrays.asList(voto1, voto2, voto3)));
    }

    @Test
    public void deveListarPorUsuario() {
        Usuario stephen = new Usuario("Stephen", "stephen@a.com");
        Usuario fulano = new Usuario("Fulano", "fulano@a.com");
        Filme harry = new Filme("Harry Potter");
        Filme senhor = new Filme("Senhor Dos Anéis");
        Voto voto1 = new Voto(stephen, harry);
        Voto voto2 = new Voto(stephen, senhor);
        Voto voto3 = new Voto(fulano, senhor);
        this.votoDAO.adiciona(voto1);
        this.votoDAO.adiciona(voto2);
        this.votoDAO.adiciona(voto3);

        List<Voto> listaPorUsuario = this.votoDAO.listaPorUsuario(stephen);

        assertThat(listaPorUsuario.size(), is(equalTo(2)));
        assertTrue(listaPorUsuario.contains(voto1));
        assertTrue(listaPorUsuario.contains(voto2));
    }

}
