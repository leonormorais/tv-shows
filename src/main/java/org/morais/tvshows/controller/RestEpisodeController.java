package org.morais.tvshows.controller;

import org.morais.tvshows.command.EpisodeDto;
import org.morais.tvshows.converters.EpisodeDtoToEpisode;
import org.morais.tvshows.converters.EpisodeToEpisodeDto;
import org.morais.tvshows.exceptions.TvShowNotFoundException;
import org.morais.tvshows.persistence.model.Episode;
import org.morais.tvshows.persistence.model.TvShow;
import org.morais.tvshows.services.EpisodeService;
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
public class RestEpisodeController {

    private TvShowService tvShowService;
    private EpisodeService episodeService;
    private EpisodeToEpisodeDto episodeToEpisodeDto;
    private EpisodeDtoToEpisode episodeDtoToEpisode;

    @Autowired
    public void setTvShowService(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @Autowired
    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @Autowired
    public void setEpisodeToEpisodeDto(EpisodeToEpisodeDto episodeToEpisodeDto) {
        this.episodeToEpisodeDto = episodeToEpisodeDto;
    }

    @Autowired
    public void setEpisodeDtoToEpisode(EpisodeDtoToEpisode episodeDtoToEpisode) {
        this.episodeDtoToEpisode = episodeDtoToEpisode;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{tvShowId}/episode")
    public ResponseEntity<List<EpisodeDto>> listTvShowEpisodes(@PathVariable Integer tvShowId) {
        TvShow tvShow = tvShowService.get(tvShowId);

        if (tvShow == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<EpisodeDto> episodeDtos = new ArrayList<>();
        for (Episode episode : tvShow.getEpisodes()) {
            episodeDtos.add(episodeToEpisodeDto.convert(episode));
        }

        return new ResponseEntity<>(episodeDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{tvShowId}/episode/{episodeId}")
    public ResponseEntity<EpisodeDto> showTvShowEpisode(@PathVariable Integer tvShowId, @PathVariable Integer episodeId) {
        Episode episode = episodeService.get(episodeId);

        if (episode == null || episode.getTvShow() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!episode.getTvShow().getId().equals(tvShowId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(episodeToEpisodeDto.convert(episode), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{tvShowId}/episode")
    public ResponseEntity<?> addEpisode(@PathVariable Integer tvShowId, @Valid @RequestBody EpisodeDto episodeDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        if (bindingResult.hasErrors() || episodeDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Episode episode = tvShowService.addEpisode(tvShowId, episodeDtoToEpisode.convert(episodeDto));

            UriComponents uriComponents = uriComponentsBuilder.path("/api/tv-show" + tvShowId + "/episode/" + episode.getId()).build();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } catch (TvShowNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
