package br.com.bluesoft.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import br.com.bluesoft.modelo.Filme;

/**
 * DAO para {@link Filme}
 * 
 * @author stephen.ribeiro
 */
@Repository
public class FilmeDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * Construtor vazio
     */
    public FilmeDAO() {
    }

    /**
     * Construtor
     * 
     * @param em entity manager
     */
    public FilmeDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Adiciona um filme
     * 
     * @param filme a ser adicionado
     */
    public void adiciona(Filme filme) {
        this.em.persist(filme);
    }

    /**
     * Verifica se o filme existe
     * 
     * @param filme a ser verificado
     * @return true se o filme existe
     */
    public boolean existe(Filme filme) {
        Query query = this.em.createQuery("from Filme where nome = :nome").setParameter("nome", filme.getNome());

        boolean encontrado = !query.getResultList().isEmpty();

        return encontrado;
    }

    /**
     * Lista todos os filmes
     * 
     * @return todos os filmes
     */
    public List<Filme> listaTodos() {
        CriteriaQuery<Filme> query = em.getCriteriaBuilder().createQuery(Filme.class);
        query.select(query.from(Filme.class));

        List<Filme> lista = em.createQuery(query).getResultList();

        return lista;
    }
}
