package org.morais.tvshows.converters;

import org.morais.tvshows.command.TvShowDto;
import org.morais.tvshows.persistence.model.TvShow;
import org.morais.tvshows.services.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TvShowDtoToTvShow {

    private TvShowService tvShowService;

    @Autowired
    public void setTvShowService(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    public TvShow convert(TvShowDto tvShowDto) {
        TvShow tvShow = tvShowDto.getId() != null ? tvShowService.get(tvShowDto.getId()) : new TvShow();
        tvShow.setName(tvShowDto.getName());
        return tvShow;
    }
}
