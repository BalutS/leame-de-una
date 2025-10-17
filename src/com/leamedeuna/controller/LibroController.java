package com.leamedeuna.controller;

import com.leamedeuna.dto.LibroDTO;
import com.leamedeuna.service.LibroService;
import java.util.List;

public class LibroController {

    private static final LibroService libroService = new LibroService();

    public static boolean crearLibro(LibroDTO dto) {
        LibroDTO respuesta = libroService.insertInto(dto, "");
        return respuesta != null;
    }

    public static List<LibroDTO> getAllLibros() {
        return libroService.selectFrom();
    }
}