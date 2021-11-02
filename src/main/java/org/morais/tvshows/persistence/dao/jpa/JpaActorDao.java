package org.morais.tvshows.persistence.dao.jpa;

import org.morais.tvshows.persistence.dao.ActorDao;
import org.morais.tvshows.persistence.model.Actor;
import org.morais.tvshows.persistence.model.Actor_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class JpaActorDao extends GenericJpaDao<Actor> implements ActorDao {

    public JpaActorDao() {
        super(Actor.class);
    }

    @Override
    public Actor findByName(String name) {
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Actor> criteriaQuery = builder.createQuery(modelType);
            Root<Actor> root = criteriaQuery.from(modelType);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get(Actor_.name), name));

            return em.createQuery(criteriaQuery).getSingleResult();

        } catch (NoResultException e) {
            return null;
        }

    }
}
