package com.leamedeuna.service;

import com.leamedeuna.api.ApiOperacionBD;
import com.leamedeuna.dto.AutorDTO;
import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorService implements ApiOperacionBD<AutorDTO, Integer> {

    private final NioFile miArchivo;
    private static final String FILE_PATH = "database/autores.txt";

    public AutorService() {
        NioFile file = null;
        try {
            file = new NioFile(FILE_PATH);
        } catch (IOException ex) {
            Logger.getLogger(AutorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.miArchivo = file;
    }

    @Override
    public int getSerial() {
        try {
            return miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(AutorService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public AutorDTO insertInto(AutorDTO objeto, String ruta) {
        int newId = getSerial();
        objeto.setId(newId);
        String row = objeto.getId() + ";" + objeto.getNombre() + ";" + objeto.getGenero();
        if (miArchivo.agregarRegistro(row)) {
            return objeto;
        }
        return null;
    }

    @Override
    public List<AutorDTO> selectFrom() {
        List<AutorDTO> autores = new ArrayList<>();
        try {
            List<String> lines = miArchivo.getAllLines();
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    AutorDTO dto = new AutorDTO();
                    dto.setId(Integer.parseInt(parts[0]));
                    dto.setNombre(parts[1]);
                    dto.setGenero(parts[2]);
                    autores.add(dto);
                }
            }
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(AutorService.class.getName()).log(Level.SEVERE, "Error reading or parsing autores.txt", e);
        }
        return autores;
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
    public AutorDTO updateSet(Integer codigo, AutorDTO objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AutorDTO getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}