package br.com.bluesoft.dao;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.bluesoft.modelo.Usuario;

public class UsuarioDAOTest {

    EntityManager entityManager;

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
        Usuario usuario = new Usuario("Stephen", "a@a.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO(this.entityManager);
        usuarioDAO.adiciona(usuario);
        assertTrue(usuarioDAO.existe(usuario));
    }

}
