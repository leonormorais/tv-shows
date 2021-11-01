package org.morais.tvshows.persistence.dao.jpa;

import org.morais.tvshows.persistence.dao.GenreDao;
import org.morais.tvshows.persistence.model.Genre;
import org.morais.tvshows.persistence.model.Genre_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class JpaGenreDao extends GenericJpaDao<Genre> implements GenreDao {

    public JpaGenreDao() {
        super(Genre.class);
    }

    @Override
    public Genre findByName(String name) {
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Genre> criteriaQuery = builder.createQuery(modelType);
            Root<Genre> root = criteriaQuery.from(modelType);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get(Genre_.name), name));

            return em.createQuery(criteriaQuery).getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }
}
