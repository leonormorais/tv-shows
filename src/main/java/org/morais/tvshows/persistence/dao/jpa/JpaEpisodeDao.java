package org.morais.tvshows.persistence.dao.jpa;

import org.morais.tvshows.persistence.dao.EpisodeDao;
import org.morais.tvshows.persistence.model.Episode;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEpisodeDao extends GenericJpaDao<Episode> implements EpisodeDao {

    public JpaEpisodeDao() {
        super(Episode.class);
    }
}
