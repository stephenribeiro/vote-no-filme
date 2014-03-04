package br.com.bluesoft.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.bluesoft.modelo.Usuario;
import br.com.bluesoft.modelo.Voto;

@Repository
public class VotoDAO {

    @PersistenceContext
    private EntityManager em;

    public VotoDAO() {
    }

    public VotoDAO(EntityManager em) {
        this.em = em;
    }

    public void adiciona(Voto voto) {
        this.em.persist(voto);
    }

    public boolean existe(Voto voto) {
        Query query =
                this.em.createQuery("from Voto where usuario = :usuario and filme = :filme").setParameter("usuario", voto.getUsuario())
                        .setParameter("filme", voto.getFilme());

        return !query.getResultList().isEmpty();
    }

    public List<Voto> listaTodos() {
        CriteriaQuery<Voto> query = this.em.getCriteriaBuilder().createQuery(Voto.class);
        query.select(query.from(Voto.class));

        List<Voto> lista = this.em.createQuery(query).getResultList();

        return lista;
    }

    public List<Voto> listaPorUsuario(Usuario usuario) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<Voto> query = cb.createQuery(Voto.class);
        Root<Voto> from = query.from(Voto.class);

        query.where(cb.equal(from.get("usuario"), usuario));

        query.select(from);

        List<Voto> lista = this.em.createQuery(query).getResultList();

        return lista;
    }

}
