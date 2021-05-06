package com.example.entrevuespringboot.controller;

import com.example.entrevuespringboot.domain.Film;
import com.example.entrevuespringboot.domain.films.FilmDto;
import com.example.entrevuespringboot.domain.films.FilmMapperImpl;
import com.example.entrevuespringboot.error.FilmAlreadyExistsException;
import com.example.entrevuespringboot.error.FilmNotFoundException;
import com.example.entrevuespringboot.services.FilmServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


/**
 * REST controller for managing FILMS
 */
@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Transactional
public class FilmController {

    private final FilmServiceImpl filmService;
    private final FilmMapperImpl filmMapper;

    @Autowired
    public FilmController(FilmServiceImpl filmService, FilmMapperImpl filmMapper){
        this.filmMapper = filmMapper;
        this.filmService = filmService;

    }

    private static final Logger LOG = LogManager.getLogger(FilmController.class);

    /**
     * @param filmDto
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} or with status {@code 400 (Bad Request)}
     * @throws FilmAlreadyExistsException
     */
    @PostMapping(value = "/film")
    public ResponseEntity<Film> createFilm(@RequestBody @Valid FilmDto filmDto) throws URISyntaxException {
        LOG.debug("REST request to save Movie : {}", filmDto);
        Film film = filmMapper.toFilm(filmDto);
        filmService.saveFilm(film);
        return ResponseEntity
                .created(new URI("/api/films/" + film.getId()))
                .body(film);
    }

    /**
     * @param id Film to retrieve
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the film, or with status {@code 404 (Not Found)}.
     * @throws FilmNotFoundException
     */
    @GetMapping(value = "/film/{id}")
    public ResponseEntity retrieveFilmById(@PathVariable("id") long id)  {

        Film film = filmService.retrieveFilmById(id);

        return ResponseEntity.ok(filmMapper.toFilmDTO(film));

    }

    /**
     * get all the actors.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the film, or with status {@code 404 (Not Found)}.
     * @throws FilmNotFoundException
     */
    @GetMapping(value = "/films")
    public ResponseEntity<List<Film>> retrieveAllFilms() {

        List<Film> films = filmService.retrieveAllFilms();

        return new ResponseEntity<>(films, HttpStatus.OK);
    }


    /**
     * {@code DELETE  /actors/:id} : delete the "id" film.
     * @param id
     * @return @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     * @throws FilmNotFoundException
     */
    @DeleteMapping(value = "/film/{id}")
    public ResponseEntity<Film> delete( @PathVariable("id") long id ){

        LOG.info("Starting delete a Film {}", id);

        filmService.deleteFilmById(id);

        return ResponseEntity.ok().build();
    }

}
