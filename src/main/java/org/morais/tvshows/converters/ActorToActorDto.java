package org.morais.tvshows.converters;

import org.morais.tvshows.command.ActorDto;
import org.morais.tvshows.persistence.model.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorToActorDto {

    public ActorDto convert(Actor actor) {
        ActorDto actorDto = new ActorDto();
        actorDto.setId(actor.getId());
        actorDto.setName(actor.getName());
        return actorDto;
    }
}
