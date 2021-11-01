package org.morais.tvshows.persistence.dao;

import org.morais.tvshows.persistence.model.TvShow;

import java.util.List;

public interface TvShowDao extends Dao<TvShow> {

    List<TvShow> findAll();

    List<TvShow> findAllByGenreName(String genreName);

    List<TvShow> findAllSortedByField(String field);

    TvShow saveOrUpdate(TvShow tvShow);

    void delete(Integer id);
}
