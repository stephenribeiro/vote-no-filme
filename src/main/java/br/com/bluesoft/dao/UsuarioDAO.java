package br.com.bluesoft.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.bluesoft.modelo.Usuario;

/**
 * DAO para {@link Usuario}
 * 
 * @author stephen.ribeiro
 */
@Repository
public class UsuarioDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    /**
     * Construtor
     */
    public UsuarioDAO() {
    }

    /**
     * Construtor
     * 
     * @param em entity manager
     */
    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Verifica se o usuário existe
     * 
     * @param usuario a ser verificado
     * @return true se o usuário existe
     */
    public boolean existe(Usuario usuario) {

        Query query =
                this.em.createQuery("from Usuario where nome = :nome and email = :email").setParameter("nome", usuario.getNome())
                        .setParameter("email", usuario.getEmail());

        boolean encontrado = !query.getResultList().isEmpty();

        return encontrado;
    }

    /**
     * Adiciona um usuário
     * 
     * @param usuario a ser adicionado
     */
    public void adiciona(Usuario usuario) {
        em.persist(usuario);
    }
}
