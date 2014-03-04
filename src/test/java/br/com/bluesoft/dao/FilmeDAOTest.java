package br.com.bluesoft.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.bluesoft.modelo.Filme;

public class FilmeDAOTest {

    private EntityManager entityManager;

    @Before
    public void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("vote-no-filme-teste");
        this.entityManager = factory.createEntityManager();
    }

    @After
    public void destroy() {
        this.entityManager.close();
    }

    @Test
    public void deveInserirCorretamente() {
        Filme filme = new Filme("Harry Potter");
        FilmeDAO filmeDAO = new FilmeDAO(this.entityManager);
        filmeDAO.adiciona(filme);
        assertTrue(filmeDAO.existe(filme));
    }

    @Test
    public void deveListarCorretamente() {
        Filme filme = new Filme("Harry Potter");
        FilmeDAO filmeDAO = new FilmeDAO(this.entityManager);
        filmeDAO.adiciona(filme);

        List<Filme> todos = filmeDAO.listaTodos();
        assertThat(todos.size(), is(equalTo(1)));
    }

}
