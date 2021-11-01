package org.morais.tvshows.converters;

import org.morais.tvshows.command.EpisodeDto;
import org.morais.tvshows.persistence.model.Episode;
import org.morais.tvshows.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EpisodeDtoToEpisode {

    private EpisodeService episodeService;

    @Autowired
    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    public Episode convert(EpisodeDto episodeDto) {
        Episode episode = episodeDto.getId() != null ? episodeService.get(episodeDto.getId()) : new Episode();
        episode.setSeason(episodeDto.getSeason());
        episode.setEpisode(episodeDto.getEpisode());
        episode.setName(episodeDto.getName());
        episode.setReleaseDate(episodeDto.getReleaseDate());
        return episode;
    }
}
