package org.morais.tvshows.controller;

import org.morais.tvshows.command.TvShowDto;
import org.morais.tvshows.converters.TvShowDtoToTvShow;
import org.morais.tvshows.converters.TvShowToTvShowDto;
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
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tv-show")
public class RestTvShowController {

    private TvShowService tvShowService;
    private GenreService genreService;
    private TvShowToTvShowDto tvShowToTvShowDto;
    private TvShowDtoToTvShow tvShowDtoToTvShow;

    @Autowired
    public void setTvShowService(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setTvShowToTvShowDto(TvShowToTvShowDto tvShowToTvShowDto) {
        this.tvShowToTvShowDto = tvShowToTvShowDto;
    }

    @Autowired
    public void setTvShowDtoToTvShow(TvShowDtoToTvShow tvShowDtoToTvShow) {
        this.tvShowDtoToTvShow = tvShowDtoToTvShow;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<TvShowDto>> listTvShows() {
        List<TvShowDto> tvShowDtos = new ArrayList<>();

        for (TvShow tvShow : tvShowService.list()) {
            tvShowDtos.add(tvShowToTvShowDto.convert(tvShow));
        }

        return new ResponseEntity<>(tvShowDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""}, params = "genre")
    public ResponseEntity<List<TvShowDto>> listTvShowsByGenre(@RequestParam(name = "genre") String genreName) {
        Genre genre = genreService.get(genreName);

        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<TvShowDto> tvShowDtos = new ArrayList<>();
        for (TvShow tvShow : tvShowService.listByGenreName(genreName)) {
            tvShowDtos.add(tvShowToTvShowDto.convert(tvShow));
        }

        return new ResponseEntity<>(tvShowDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""}, params = "sortBy")
    public ResponseEntity<List<TvShowDto>> listTvShowsSortedByField(@RequestParam(name = "sortBy") String field) {
        if (!doesTvShowContainField(field)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<TvShowDto> tvShowDtos = new ArrayList<>();
        for (TvShow tvShow : tvShowService.listSortedByField(field)) {
            tvShowDtos.add(tvShowToTvShowDto.convert(tvShow));
        }

        return new ResponseEntity<>(tvShowDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<TvShowDto> showTvShow(@PathVariable Integer id) {
        TvShow tvShow = tvShowService.get(id);

        if (tvShow == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tvShowToTvShowDto.convert(tvShow), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addTvShow(@Valid @RequestBody TvShowDto tvShowDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        if (bindingResult.hasErrors() || tvShowDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TvShow tvShow = tvShowService.save(tvShowDtoToTvShow.convert(tvShowDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/tv-show/" + tvShow.getId()).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<TvShowDto> deleteTvShow(@PathVariable Integer id) {
        try {
            tvShowService.delete(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (TvShowNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private boolean doesTvShowContainField(String fieldName) {
        for (Field field : TvShowDto.class.getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                return true;
            }
        }

        return false;
    }

}
