package org.morais.tvshows.controller;

import org.morais.tvshows.command.GenreDto;
import org.morais.tvshows.converters.GenreDtoToGenre;
import org.morais.tvshows.converters.GenreToGenreDto;
import org.morais.tvshows.exceptions.TvShowNotFoundException;
import org.morais.tvshows.persistence.model.Genre;
import org.morais.tvshows.persistence.model.TvShow;
import org.morais.tvshows.services.GenreService;
import org.morais.tvshows.services.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/tv-show")
public class RestGenreController {

    private TvShowService tvShowService;
    private GenreService genreService;
    private GenreToGenreDto genreToGenreDto;
    private GenreDtoToGenre genreDtoToGenre;

    @Autowired
    public void setTvShowService(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setGenreToGenreDto(GenreToGenreDto genreToGenreDto) {
        this.genreToGenreDto = genreToGenreDto;
    }

    @Autowired
    public void setGenreDtoToGenre(GenreDtoToGenre genreDtoToGenre) {
        this.genreDtoToGenre = genreDtoToGenre;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{tvShowId}/genre")
    public ResponseEntity<List<GenreDto>> listTvShowGenres(@PathVariable Integer tvShowId) {
        TvShow tvShow = tvShowService.get(tvShowId);

        if (tvShow == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<GenreDto> genreDtos = new ArrayList<>();
        for (Genre genre : tvShow.getGenres()) {
            genreDtos.add(genreToGenreDto.convert(genre));
        }

        return new ResponseEntity<>(genreDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{tvShowId}/genre/{genreId}")
    public ResponseEntity<GenreDto> showTvShowGenre(@PathVariable Integer tvShowId, @PathVariable Integer genreId) {
        TvShow tvShow = tvShowService.get(tvShowId);
        Genre genre = genreService.get(genreId);

        if (genre == null || !genre.getTvShows().contains(tvShow)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(genreToGenreDto.convert(genre), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{tvShowId}/genre")
    public ResponseEntity<?> addGenre(@PathVariable Integer tvShowId, @Valid @RequestBody GenreDto genreDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        if (bindingResult.hasErrors() || genreDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Genre genre = tvShowService.addGenre(tvShowId, genreDtoToGenre.convert(genreDto));

            UriComponents uriComponents = uriComponentsBuilder.path("/api/tv-show" + tvShowId + "/genre/" + genre.getId()).build();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } catch (TvShowNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
