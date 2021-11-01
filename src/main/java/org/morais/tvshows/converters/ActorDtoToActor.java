package org.morais.tvshows.converters;

import org.morais.tvshows.command.ActorDto;
import org.morais.tvshows.persistence.model.Actor;
import org.morais.tvshows.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActorDtoToActor {

    private ActorService actorService;

    @Autowired
    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    public Actor convert(ActorDto actorDto) {
        Actor actor = actorDto.getId() != null ? actorService.get(actorDto.getId()) : new Actor();
        actor.setName(actorDto.getName());
        return actor;
    }
}
