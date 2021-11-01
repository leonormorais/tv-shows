package org.morais.tvshows.persistence.dao;

import org.morais.tvshows.persistence.model.Model;

import java.util.List;

public interface Dao<T extends Model> {

    T findById(Integer id);

}
