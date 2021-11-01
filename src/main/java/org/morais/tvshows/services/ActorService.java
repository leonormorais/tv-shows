package org.morais.tvshows.services;

import org.morais.tvshows.persistence.dao.ActorDao;
import org.morais.tvshows.persistence.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private ActorDao actorDao;

    @Autowired
    public void setActorDao(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public Actor get(Integer id) {
        return actorDao.findById(id);
    }
}
