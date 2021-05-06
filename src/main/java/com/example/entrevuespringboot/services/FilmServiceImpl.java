package com.example.entrevuespringboot.services;

import com.example.entrevuespringboot.domain.Film;
import com.example.entrevuespringboot.error.FilmAlreadyExistsException;
import com.example.entrevuespringboot.error.FilmNotFoundException;
import com.example.entrevuespringboot.repository.FilmRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    private static final Logger LOGGER = LogManager.getLogger(FilmServiceImpl.class);

    @Transactional
    @Override
    public Film saveFilm(Film film){

        LOGGER.info("Updating Film: {}", film.getId());

        if (filmRepository.existsById(film.getId())) {
            LOGGER.error("Error updating Film: {}", film.getId());
            throw new FilmAlreadyExistsException("Error updating Film: " + film.getId());
        }

        return filmRepository.save(film);
    }

    @Override
    public List<Film> retrieveAllFilms() {

        LOGGER.info("retrieving all Films");
        return filmRepository.findAll();
    }

    @Override
    public Film retrieveFilmById(long id) {

        return filmRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Error retrieving Film: {}" , id);
                    return new FilmNotFoundException("Film not found for this id : " + id);
                });
    }

    @Transactional
    @Override
    public Boolean deleteFilmById(long id){

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Error deleting Film: {}", id);
                    return new FilmNotFoundException("Can't delete. Film not found for this id:  " +  id);

                });

        filmRepository.delete(film);

        return Boolean.TRUE;

    }

}