package com.example.entrevuespringboot.domain.films;


import com.example.entrevuespringboot.domain.Actor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class FilmDto {

    private long id;
    private String titre;
    private String description;
    private List<Actor> acteurs = new ArrayList<>();

}
