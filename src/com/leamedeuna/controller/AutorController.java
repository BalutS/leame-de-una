package com.leamedeuna.controller;

import com.leamedeuna.dto.AutorDTO;
import com.leamedeuna.dto.LibroDTO;
import com.leamedeuna.service.AutorService;
import com.leamedeuna.service.LibroService;
import java.util.List;

public class AutorController {

    private static final AutorService autorService = new AutorService();
    private static final LibroService libroService = new LibroService();

    public static List<AutorDTO> getAllAutores() {
        List<AutorDTO> autores = autorService.selectFrom();
        List<LibroDTO> libros = libroService.selectFrom();

        for (AutorDTO autor : autores) {
            long count = libros.stream().filter(libro -> libro.getAutor() != null && libro.getAutor().getId() == autor.getId()).count();
            autor.setCantidadLibros((int) count);
        }

        return autores;
    }
}