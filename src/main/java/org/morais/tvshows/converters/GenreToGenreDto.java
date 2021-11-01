package org.morais.tvshows.converters;

import org.morais.tvshows.command.GenreDto;
import org.morais.tvshows.persistence.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreToGenreDto {

    public GenreDto convert(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }
}
