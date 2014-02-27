package br.com.bluesoft.dao;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.bluesoft.dao.FilmeDAO;
import br.com.bluesoft.modelo.Filme;

public class FilmeDAOTest {

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
        Filme filme = new Filme("Harry Potter");
        FilmeDAO filmeDAO = new FilmeDAO(this.entityManager);
        filmeDAO.adiciona(filme);
        assertTrue(filmeDAO.existe(filme));
    }

}
