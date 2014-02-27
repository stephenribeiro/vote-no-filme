package br.com.bluesoft.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.bluesoft.modelo.Filme;
import br.com.bluesoft.modelo.Voto;

public class VotoDAO {

    private EntityManager em;

    @Inject
    public VotoDAO(EntityManager em) {
        this.em = em;
    }

    public void adiciona(Voto voto) {
        em.getTransaction().begin();
        em.persist(voto);
        em.getTransaction().commit();
    }

    public boolean existe(Voto voto) {
        this.em.getTransaction().begin();

        Query query =
                this.em.createQuery("from Voto where usuario = :usuario and filme = :filme").setParameter("usuario", voto.getUsuario())
                        .setParameter("filme", voto.getFilme());

        boolean encontrado = !query.getResultList().isEmpty();
        this.em.getTransaction().commit();

        return encontrado;
    }

    public List<Voto> listaTodos() {
        EntityManager em = new JPAUtil().getEntityManager();
        CriteriaQuery<Voto> query = em.getCriteriaBuilder().createQuery(Voto.class);
        query.select(query.from(Voto.class));

        List<Voto> lista = em.createQuery(query).getResultList();

        return lista;
    }

    public Map<Filme, Integer> agregarVotos() {
        List<Object[]> resultList =
                this.em.createQuery(
                        "SELECT DISTINCT v.filme AS filme, COUNT(v) AS votos FROM Voto AS v GROUP BY v.filme ORDER BY v.filme ASC")
                        .getResultList();
        Map<Filme, Integer> hashMap = new HashMap<Filme, Integer>();

        for (Object[] result : resultList) {
            Filme filme = (Filme) result[0];
            int votos = ((Number) result[1]).intValue();
            hashMap.put(filme, votos);
        }

        return hashMap;

        // CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
        // // CriteriaQuery<Tuple> createTupleQuery = criteriaBuilder.createTupleQuery();
        // CriteriaQuery<Voto> criteria = criteriaBuilder.createQuery(Voto.class);
        // Root<Voto> from = criteria.from(Voto.class);
        //
        // // criteria.select(root.get("filme"));
        // criteria.multiselect(from.alias("filme"));
        // criteria.groupBy(from.get("filme"));
        // criteria.distinct(true);
        //
        // criteria.get

        // return null;
    }
}
