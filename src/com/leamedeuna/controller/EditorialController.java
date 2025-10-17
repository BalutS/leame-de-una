package com.leamedeuna.controller;

import com.leamedeuna.dto.EditorialDTO;
import com.leamedeuna.dto.LibroDTO;
import com.leamedeuna.service.EditorialService;
import com.leamedeuna.service.LibroService;
import java.util.List;

public class EditorialController {

    private static final EditorialService editorialService = new EditorialService();
    private static final LibroService libroService = new LibroService();

    public static List<EditorialDTO> getAllEditoriales() {
        List<EditorialDTO> editoriales = editorialService.selectFrom();
        List<LibroDTO> libros = libroService.selectFrom();

        for (EditorialDTO editorial : editoriales) {
            long count = libros.stream().filter(libro -> libro.getEditorial() != null && libro.getEditorial().getId() == editorial.getId()).count();
            editorial.setCantidadLibros((int) count);
        }

        return editoriales;
    }
}