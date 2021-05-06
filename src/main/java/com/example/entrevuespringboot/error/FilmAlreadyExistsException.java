package com.example.entrevuespringboot.error;

public class FilmAlreadyExistsException extends RuntimeException  {

    private static final long serialVersionUID = 1L;

    public FilmAlreadyExistsException(String message) {
        super(message);

    }



}
