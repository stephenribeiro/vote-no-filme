package br.com.bluesoft.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import br.com.bluesoft.modelo.Filme;

@Repository
public class FilmeDAO {

    @PersistenceContext
    private EntityManager em;

    public FilmeDAO() {
    }

    public FilmeDAO(EntityManager em) {
        this.em = em;
    }

    public void adiciona(Filme filme) {
        this.em.persist(filme);
    }

    public boolean existe(Filme filme) {
        Query query = this.em.createQuery("from Filme where nome = :nome").setParameter("nome", filme.getNome());

        boolean encontrado = !query.getResultList().isEmpty();

        return encontrado;
    }

    public List<Filme> listaTodos() {
        // return Arrays.asList(new Filme("Harry Potter"), new Filme("Senhor dos Anéis"), new Filme("Armagedon"), new Filme("A Origem"),
        // new Filme("300")); // provisório

        CriteriaQuery<Filme> query = em.getCriteriaBuilder().createQuery(Filme.class);
        query.select(query.from(Filme.class));

        List<Filme> lista = em.createQuery(query).getResultList();

        return lista;
    }
}
