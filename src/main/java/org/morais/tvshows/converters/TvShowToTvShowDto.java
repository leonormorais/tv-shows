package org.morais.tvshows.converters;

import org.morais.tvshows.command.TvShowDto;
import org.morais.tvshows.persistence.model.TvShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TvShowToTvShowDto {

    private GenreToGenreDto genreToGenreDto;

    @Autowired
    public void setGenreToGenreDto(GenreToGenreDto genreToGenreDto) {
        this.genreToGenreDto = genreToGenreDto;
    }

    public TvShowDto convert(TvShow tvShow) {
        TvShowDto tvShowDto = new TvShowDto();
        tvShowDto.setId(tvShow.getId());
        tvShowDto.setName(tvShow.getName());
        return tvShowDto;
    }
}
