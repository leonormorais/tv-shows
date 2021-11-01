package org.morais.tvshows.services;

import org.morais.tvshows.exceptions.TvShowNotFoundException;
import org.morais.tvshows.persistence.dao.ActorDao;
import org.morais.tvshows.persistence.dao.GenreDao;
import org.morais.tvshows.persistence.dao.TvShowDao;
import org.morais.tvshows.persistence.model.Actor;
import org.morais.tvshows.persistence.model.Episode;
import org.morais.tvshows.persistence.model.Genre;
import org.morais.tvshows.persistence.model.TvShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TvShowService {

    private TvShowDao tvShowDao;
    private GenreDao genreDao;
    private ActorDao actorDao;

    @Autowired
    public void setTvShowDao(TvShowDao tvShowDao) {
        this.tvShowDao = tvShowDao;
    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Autowired
    public void setActorDao(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public TvShow get(Integer id) {
        return tvShowDao.findById(id);
    }

    public List<TvShow> list() {
        return tvShowDao.findAll();
    }

    public List<TvShow> listByGenreName(String genreName) {
        return tvShowDao.findAllByGenreName(genreName);
    }

    public List<TvShow> listSortedByField(String field) {
        return tvShowDao.findAllSortedByField(field);
    }

    @Transactional
    public TvShow save(TvShow tvShow) {
        return tvShowDao.saveOrUpdate(tvShow);
    }

    @Transactional
    public Genre addGenre(Integer tvShowId, Genre genre) throws TvShowNotFoundException {
        TvShow tvShow = tvShowDao.findById(tvShowId);

        if (tvShow == null) {
            throw new TvShowNotFoundException();
        }

        Genre foundGenre = genreDao.findByName(genre.getName());

        if (foundGenre != null) {
            genre = foundGenre;
        }

        tvShow.addGenre(genre);
        tvShowDao.saveOrUpdate(tvShow);

        return tvShow.getGenres().get(tvShow.getGenres().size() - 1);
    }

    @Transactional
    public Actor addActor(Integer tvShowId, Actor actor) throws TvShowNotFoundException {
        TvShow tvShow = tvShowDao.findById(tvShowId);

        if (tvShow == null) {
            throw new TvShowNotFoundException();
        }

        Actor foundActor = actorDao.findByName(actor.getName());

        if (foundActor != null) {
            actor = foundActor;
        }

        tvShow.addActor(actor);
        tvShowDao.saveOrUpdate(tvShow);

        return tvShow.getActors().get(tvShow.getActors().size() - 1);
    }

    @Transactional
    public Episode addEpisode(Integer tvShowId, Episode episode) throws TvShowNotFoundException {
        TvShow tvShow = tvShowDao.findById(tvShowId);

        if (tvShow == null) {
            throw new TvShowNotFoundException();
        }

        tvShow.addEpisode(episode);
        tvShowDao.saveOrUpdate(tvShow);

        return tvShow.getEpisodes().get(tvShow.getEpisodes().size() - 1);
    }

    @Transactional
    public void delete(Integer id) throws TvShowNotFoundException {
        TvShow tvShow = tvShowDao.findById(id);

        if (tvShow == null) {
            throw new TvShowNotFoundException();
        }

        tvShow.removeAllGenres();
        tvShow.removeAllActors();
        tvShow.removeAllEpisodes();

        tvShowDao.saveOrUpdate(tvShow);
        tvShowDao.delete(id);
    }
}
