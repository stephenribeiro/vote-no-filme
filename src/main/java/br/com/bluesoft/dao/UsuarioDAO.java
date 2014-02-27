package br.com.bluesoft.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bluesoft.modelo.Usuario;

public class UsuarioDAO implements Serializable {

    private EntityManager em;

    @Inject
    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public boolean existe(Usuario usuario) {
        this.em.getTransaction().begin();

        Query query =
                this.em.createQuery("from Usuario where nome = :nome and email = :email").setParameter("nome", usuario.getNome())
                        .setParameter("email", usuario.getEmail());

        boolean encontrado = !query.getResultList().isEmpty();
        this.em.getTransaction().commit();

        return encontrado;
    }

    public void adiciona(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }
}
