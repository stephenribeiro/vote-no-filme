package br.com.bluesoft.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {
    private final Class<T> classe;

    EntityManager em;

    public DAO(EntityManager em, Class<T> classe) {
        this.classe = classe;
        this.em = em;
    }

    public void adiciona(T t) {
        // abre transacao
        em.getTransaction().begin();

        // persiste o objeto
        em.persist(t);

        // commita a transacao
        em.getTransaction().commit();

        // fecha a entity manager
        em.close();
    }

    public void remove(T t) {
        em.getTransaction().begin();

        em.remove(em.merge(t));

        em.getTransaction().commit();
        em.close();
    }

    public void atualiza(T t) {
        em.getTransaction().begin();

        em.merge(t);

        em.getTransaction().commit();
        em.close();
    }

    public List<T> listaTodos() {
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));

        List<T> lista = em.createQuery(query).getResultList();

        em.close();
        return lista;
    }

    public T buscaPorId(Long id) {
        T instancia = em.find(classe, id);
        em.close();
        return instancia;
    }

    public int contaTodos() {
        long result = (Long) em.createQuery("select count(n) from " + classe.getName() + " n").getSingleResult();
        em.close();

        return (int) result;
    }

    public List<T> listaTodosPaginada(int firstResult, int maxResults) {
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));

        List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

        em.close();
        return lista;
    }

}
