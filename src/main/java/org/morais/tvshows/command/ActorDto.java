package org.morais.tvshows.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ActorDto {

    private Integer id;

    @NotNull(message = "Tv show name is mandatory")
    @NotBlank(message = "Tv show name is mandatory")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
