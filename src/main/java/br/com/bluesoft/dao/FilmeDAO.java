package br.com.bluesoft.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bluesoft.modelo.Filme;

public class FilmeDAO {

    private EntityManager em;

    @Inject
    public FilmeDAO(EntityManager em) {
        this.em = em;
    }

    public void adiciona(Filme filme) {
        em.getTransaction().begin();
        em.persist(filme);
        em.getTransaction().commit();
    }

    public boolean existe(Filme filme) {
        this.em.getTransaction().begin();

        Query query = this.em.createQuery("from Filme where nome = :nome").setParameter("nome", filme.getNome());

        boolean encontrado = !query.getResultList().isEmpty();
        this.em.getTransaction().commit();

        return encontrado;
    }
}
