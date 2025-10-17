package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.GeneroDto;
import org.unimag.dto.PeliculaDto;
import org.unimag.modelo.Genero;
import org.unimag.modelo.Pelicula;
import org.unimag.recurso.constante.Persistencia;

public class PeliculaServicio implements ApiOperacionBD<PeliculaDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public PeliculaServicio() {
        nombrePersistencia = Persistencia.NOMBRE_PELICULA;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(PeliculaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(PeliculaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    @Override
    public PeliculaDto insertInto(PeliculaDto dto, String ruta) {
        Genero objGenero = new Genero(
                dto.getIdGeneroPelicula().getIdGenero(), 
                dto.getIdGeneroPelicula().getNombreGenero(), 
                dto.getIdGeneroPelicula().getEstadoGenero());
        
        Pelicula objPelicula = new Pelicula();
        objPelicula.setIdPelicula(getSerial());
        objPelicula.setNombrePelicula(dto.getNombrePelicula());
        objPelicula.setProtagonistaPelicula(dto.getProtagonistaPelicula());
        objPelicula.setIdGeneroPelicula(objGenero);
        objPelicula.setPresupuestoPelicula(dto.getPresupuestoPelicula());
        objPelicula.setEstadoPelicula(dto.getEstadoPelicula());

        String filaGrabar = objPelicula.getIdPelicula() + Persistencia.SEPARADOR_COLUMNAS
                + objPelicula.getNombrePelicula() + Persistencia.SEPARADOR_COLUMNAS
                + objPelicula.getProtagonistaPelicula() + Persistencia.SEPARADOR_COLUMNAS
                + objPelicula.getIdGeneroPelicula().getIdGenero()
                + Persistencia.SEPARADOR_COLUMNAS
                + String.format("%.2f", objPelicula.getPresupuestoPelicula()) + Persistencia.SEPARADOR_COLUMNAS
                + objPelicula.getEstadoPelicula();
        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdPelicula(objPelicula.getIdPelicula());
            return dto;

        }
        return dto;

    }

    @Override
    public List<PeliculaDto> selectFrom() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int numRows() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean delectFrom(Integer coidgo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PeliculaDto updateSet(Integer codigo, PeliculaDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PeliculaDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

