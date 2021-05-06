package com.example.entrevuespringboot.domain.films;

import com.example.entrevuespringboot.domain.Film;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilmMapper {

    FilmDto toFilmDTO(Film film);

    Film toFilm(FilmDto filmDTO);

}