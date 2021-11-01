package org.morais.tvshows.persistence.dao;

import org.morais.tvshows.persistence.model.Actor;

public interface ActorDao extends Dao<Actor> {

    Actor findByName(String name);
}
