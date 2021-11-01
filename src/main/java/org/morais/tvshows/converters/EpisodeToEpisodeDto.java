package org.morais.tvshows.converters;

import org.morais.tvshows.command.EpisodeDto;
import org.morais.tvshows.persistence.model.Episode;
import org.springframework.stereotype.Component;

@Component
public class EpisodeToEpisodeDto {

    public EpisodeDto convert(Episode episode) {
        EpisodeDto episodeDto = new EpisodeDto();
        episodeDto.setId(episode.getId());
        episodeDto.setSeason(episode.getSeason());
        episodeDto.setEpisode(episode.getEpisode());
        episodeDto.setName(episode.getName());
        episodeDto.setReleaseDate(episode.getReleaseDate());
        return episodeDto;
    }
}
