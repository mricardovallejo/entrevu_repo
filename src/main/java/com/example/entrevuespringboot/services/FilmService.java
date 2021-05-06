 package com.example.entrevuespringboot.services;

 import com.example.entrevuespringboot.domain.Film;

 import java.util.List;


 public interface FilmService {

     Film saveFilm(Film film);

     List<Film> retrieveAllFilms();

     Film retrieveFilmById(long id);

     Boolean deleteFilmById(long id);

 }

