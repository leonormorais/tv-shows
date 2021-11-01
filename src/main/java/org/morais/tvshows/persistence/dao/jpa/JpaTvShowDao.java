package org.morais.tvshows.persistence.dao.jpa;

import org.morais.tvshows.persistence.dao.TvShowDao;
import org.morais.tvshows.persistence.model.Genre;
import org.morais.tvshows.persistence.model.Genre_;
import org.morais.tvshows.persistence.model.TvShow;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class JpaTvShowDao extends GenericJpaDao<TvShow> implements TvShowDao {

    public JpaTvShowDao() {
        super(TvShow.class);
    }

    @Override
    public List<TvShow> findAll() {
        CriteriaQuery<TvShow> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
        Root<TvShow> root = criteriaQuery.from(modelType);
        criteriaQuery.select(root);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<TvShow> findAllByGenreName(String genreName) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TvShow> criteriaQuery = builder.createQuery(modelType);

        Root<Genre> root = criteriaQuery.from(Genre.class);
        ListJoin<Genre, TvShow> listJoin = root.join(Genre_.tvShows);
        criteriaQuery.where(builder.equal(root.get(Genre_.name), genreName));

        return em.createQuery(criteriaQuery.select(listJoin)).getResultList();
    }

    @Override
    public List<TvShow> findAllSortedByField(String field) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TvShow> criteriaQuery = builder.createQuery(modelType);
        Root<TvShow> root = criteriaQuery.from(modelType);
        criteriaQuery.select(root);
        criteriaQuery.orderBy(builder.asc(root.get(field)));

        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public TvShow saveOrUpdate(TvShow tvShow) {
        return em.merge(tvShow);
    }

    @Override
    public void delete(Integer id) {
        em.remove(em.find(modelType, id));
    }
}
