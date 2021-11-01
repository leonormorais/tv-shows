package org.morais.tvshows.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tv_show")
public class TvShow extends AbstractModel {

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "tvShow",
            fetch = FetchType.LAZY
    )
    private List<Episode> episodes = new ArrayList<>();

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.addTvShow(this);
    }

    public void addActor(Actor actor) {
        actors.add(actor);
        actor.addTvShow(this);
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
        episode.setTvShow(this);
    }

    public void removeAllGenres() {
        for (Genre genre : genres) {
            genre.getTvShows().remove(this);
        }

        genres.clear();
    }

    public void removeAllActors() {
        for (Actor actor : actors) {
            actor.getTvShows().remove(this);
        }

        actors.clear();
    }

    public void removeAllEpisodes() {
        for (Episode episode : episodes) {
            episode.setTvShow(null);
        }

        episodes.clear();
    }
}
