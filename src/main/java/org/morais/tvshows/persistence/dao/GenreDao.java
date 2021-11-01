package org.morais.tvshows.persistence.dao;

import org.morais.tvshows.persistence.model.Genre;

public interface GenreDao extends Dao<Genre> {

    Genre findByName(String name);
}
