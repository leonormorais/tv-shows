package org.morais.tvshows.services;

import org.morais.tvshows.persistence.dao.GenreDao;
import org.morais.tvshows.persistence.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private GenreDao genreDao;

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public Genre get(Integer id) {
        return genreDao.findById(id);
    }

    public Genre get(String name) {
        return genreDao.findByName(name);
    }
}
