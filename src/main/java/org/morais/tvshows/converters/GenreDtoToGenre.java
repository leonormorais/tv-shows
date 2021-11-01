package org.morais.tvshows.converters;

import org.morais.tvshows.command.GenreDto;
import org.morais.tvshows.persistence.model.Genre;
import org.morais.tvshows.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoToGenre {

    private GenreService genreService;

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    public Genre convert(GenreDto genreDto) {
        Genre genre = genreDto.getId() != null ? genreService.get(genreDto.getId()) : new Genre();
        genre.setName(genreDto.getName());
        return genre;
    }
}
