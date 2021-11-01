package org.morais.tvshows.command;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class EpisodeDto {

    private Integer id;

    @NotNull(message = "Episode season number is mandatory")
    private Integer season;

    @NotNull(message = "Episode episode number is mandatory")
    private Integer episode;

    @NotNull(message = "Episode name is mandatory")
    @NotBlank(message = "Episode name is mandatory")
    private String name;

    @NotNull(message = "Episode release data is mandatory")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date releaseDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
