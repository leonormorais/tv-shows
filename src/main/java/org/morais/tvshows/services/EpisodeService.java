package org.morais.tvshows.services;

import org.morais.tvshows.persistence.dao.EpisodeDao;
import org.morais.tvshows.persistence.model.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService {

    private EpisodeDao episodeDao;

    @Autowired
    public void setEpisodeDao(EpisodeDao episodeDao) {
        this.episodeDao = episodeDao;
    }

    public Episode get(Integer id) {
        return episodeDao.findById(id);
    }
}
