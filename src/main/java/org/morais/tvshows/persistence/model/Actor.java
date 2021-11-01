package org.morais.tvshows.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor extends AbstractModel {

    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "actors",
            fetch = FetchType.LAZY
    )
    private List<TvShow> tvShows = new ArrayList<>();

    public List<TvShow> getTvShows() {
        return tvShows;
    }

    public void addTvShow(TvShow tvShow) {
        tvShows.add(tvShow);
    }
}
