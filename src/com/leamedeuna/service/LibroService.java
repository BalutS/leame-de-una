package com.leamedeuna.service;

import com.leamedeuna.api.ApiOperacionBD;
import com.leamedeuna.dto.AutorDTO;
import com.leamedeuna.dto.EditorialDTO;
import com.leamedeuna.dto.LibroDTO;
import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibroService implements ApiOperacionBD<LibroDTO, Integer> {

    private final NioFile miArchivo;
    private static final String FILE_PATH = "database/libros.txt";
    private final EditorialService editorialService;
    private final AutorService autorService;

    public LibroService() {
        NioFile file = null;
        try {
            file = new NioFile(FILE_PATH);
        } catch (IOException ex) {
            Logger.getLogger(LibroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.miArchivo = file;
        this.editorialService = new EditorialService();
        this.autorService = new AutorService();
    }

    @Override
    public int getSerial() {
        try {
            return miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(LibroService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public LibroDTO insertInto(LibroDTO objeto, String ruta) {
        int newId = getSerial();
        objeto.setId(newId);
        String row = objeto.getId() + ";" + objeto.getNombre() + ";" + String.format("%.2f", objeto.getPrecio()) + ";" + objeto.getAnio() + ";" + objeto.getEditorial().getId() + ";" + objeto.getAutor().getId();
        if (miArchivo.agregarRegistro(row)) {
            return objeto;
        }
        return null;
    }

    @Override
    public List<LibroDTO> selectFrom() {
        List<LibroDTO> libros = new ArrayList<>();
        try {
            List<String> lines = miArchivo.getAllLines();
            List<EditorialDTO> allEditoriales = editorialService.selectFrom();
            List<AutorDTO> allAutores = autorService.selectFrom();

            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    LibroDTO dto = new LibroDTO();
                    dto.setId(Integer.parseInt(parts[0]));
                    dto.setNombre(parts[1]);
                    dto.setPrecio(Double.parseDouble(parts[2].replace(',', '.')));
                    dto.setAnio(Integer.parseInt(parts[3]));
                    int editorialId = Integer.parseInt(parts[4]);
                    int autorId = Integer.parseInt(parts[5]);

                    EditorialDTO editorial = allEditoriales.stream().filter(e -> e.getId() == editorialId).findFirst().orElse(null);
                    AutorDTO autor = allAutores.stream().filter(a -> a.getId() == autorId).findFirst().orElse(null);

                    dto.setEditorial(editorial);
                    dto.setAutor(autor);
                    libros.add(dto);
                }
            }
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(LibroService.class.getName()).log(Level.SEVERE, "Error reading or parsing libros.txt", e);
        }
        return libros;
    }

    @Override
    public int numRows() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LibroDTO updateSet(Integer codigo, LibroDTO objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LibroDTO getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}