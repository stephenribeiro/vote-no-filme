package br.com.bluesoft.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.bluesoft.modelo.Usuario;

@Repository
public class UsuarioDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public UsuarioDAO() {
    }

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public boolean existe(Usuario usuario) {

        Query query =
                this.em.createQuery("from Usuario where nome = :nome and email = :email").setParameter("nome", usuario.getNome())
                        .setParameter("email", usuario.getEmail());

        boolean encontrado = !query.getResultList().isEmpty();

        return encontrado;
    }

    public void adiciona(Usuario usuario) {
        em.persist(usuario);
    }
}
