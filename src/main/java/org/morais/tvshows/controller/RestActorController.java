package org.morais.tvshows.controller;

import org.morais.tvshows.command.ActorDto;
import org.morais.tvshows.converters.ActorDtoToActor;
import org.morais.tvshows.converters.ActorToActorDto;
import org.morais.tvshows.exceptions.TvShowNotFoundException;
import org.morais.tvshows.persistence.model.Actor;
import org.morais.tvshows.persistence.model.TvShow;
import org.morais.tvshows.services.ActorService;
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
@RequestMapping("/api/tv-show")
public class RestActorController {

    private TvShowService tvShowService;
    private ActorService actorService;
    private ActorToActorDto actorToActorDto;
    private ActorDtoToActor actorDtoToActor;

    @Autowired
    public void setTvShowService(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @Autowired
    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    @Autowired
    public void setActorToActorDto(ActorToActorDto actorToActorDto) {
        this.actorToActorDto = actorToActorDto;
    }

    @Autowired
    public void setActorDtoToActor(ActorDtoToActor actorDtoToActor) {
        this.actorDtoToActor = actorDtoToActor;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{tvShowId}/actor")
    public ResponseEntity<List<ActorDto>> listTvShowActors(@PathVariable Integer tvShowId) {
        TvShow tvShow = tvShowService.get(tvShowId);

        if (tvShow == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ActorDto> actorDtos = new ArrayList<>();
        for (Actor actor : tvShow.getActors()) {
            actorDtos.add(actorToActorDto.convert(actor));
        }

        return new ResponseEntity<>(actorDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{tvShowId}/actor/{actorId}")
    public ResponseEntity<ActorDto> showTvShowActor(@PathVariable Integer tvShowId, @PathVariable Integer actorId) {
        TvShow tvShow = tvShowService.get(tvShowId);
        Actor actor = actorService.get(actorId);

        if (actor == null || !actor.getTvShows().contains(tvShow)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actorToActorDto.convert(actor), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{tvShowId}/actor")
    public ResponseEntity<?> addActor(@PathVariable Integer tvShowId, @Valid @RequestBody ActorDto actorDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        if (bindingResult.hasErrors() || actorDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Actor actor = tvShowService.addActor(tvShowId, actorDtoToActor.convert(actorDto));

            UriComponents uriComponents = uriComponentsBuilder.path("/api/tv-show/" + tvShowId + "/actor/" + actor.getId()).build();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } catch (TvShowNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
