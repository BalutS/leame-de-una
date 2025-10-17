package com.leamedeuna.service;

import com.leamedeuna.api.ApiOperacionBD;
import com.leamedeuna.dto.EditorialDTO;
import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditorialService implements ApiOperacionBD<EditorialDTO, Integer> {

    private final NioFile miArchivo;
    private static final String FILE_PATH = "database/editoriales.txt";

    public EditorialService() {
        NioFile file = null;
        try {
            file = new NioFile(FILE_PATH);
        } catch (IOException ex) {
            Logger.getLogger(EditorialService.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.miArchivo = file;
    }

    @Override
    public int getSerial() {
        try {
            return miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(EditorialService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public EditorialDTO insertInto(EditorialDTO objeto, String ruta) {
        int newId = getSerial();
        objeto.setId(newId);
        String row = objeto.getId() + ";" + objeto.getNombre() + ";" + objeto.getPais() + ";" + objeto.getFormato();
        if (miArchivo.agregarRegistro(row)) {
            return objeto;
        }
        return null;
    }

    @Override
    public List<EditorialDTO> selectFrom() {
        List<EditorialDTO> editoriales = new ArrayList<>();
        try {
            List<String> lines = miArchivo.getAllLines();
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    EditorialDTO dto = new EditorialDTO();
                    dto.setId(Integer.parseInt(parts[0]));
                    dto.setNombre(parts[1]);
                    dto.setPais(parts[2]);
                    dto.setFormato(Integer.parseInt(parts[3]));
                    editoriales.add(dto);
                }
            }
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(EditorialService.class.getName()).log(Level.SEVERE, "Error reading or parsing editoriales.txt", e);
        }
        return editoriales;
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
    public EditorialDTO updateSet(Integer codigo, EditorialDTO objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EditorialDTO getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}