package com.example.entrevuespringboot.domain.films;

import com.example.entrevuespringboot.domain.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapperImpl implements FilmMapper {

    @Override
    public FilmDto toFilmDTO(Film film) {
        if (film == null){
            return null;
        }
        FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setDescription(film.getDescription());
        filmDto.setTitre(film.getTitre());
        filmDto.setActeurs(film.getActeurs());
        return filmDto;
    }


    @Override
    public Film toFilm(FilmDto filmDTO) {
        if (filmDTO == null){
            return null;
        }
        Film film = new Film();
        film.setId(filmDTO.getId());
        film.setDescription(filmDTO.getDescription());
        film.setTitre(filmDTO.getTitre());
        film.setActeurs(filmDTO.getActeurs());
        return film;
    }
}
