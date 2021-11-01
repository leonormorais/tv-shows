package org.morais.tvshows.persistence.dao.jpa;

import org.morais.tvshows.persistence.dao.Dao;
import org.morais.tvshows.persistence.model.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericJpaDao<T extends Model> implements Dao<T> {

    protected Class<T> modelType;

    @PersistenceContext
    protected EntityManager em;

    public GenericJpaDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public T findById(Integer id) {
        return em.find(modelType, id);
    }
}
