package org.unimag.controlador;

import org.unimag.dto.PeliculaDto;
import org.unimag.servicio.PeliculaServicio;

public class PeliculaControladorGrabar {
        public static Boolean crearPelicula (PeliculaDto dto){
        Boolean correcto = false;
        
        PeliculaServicio peliculaServicio = new PeliculaServicio();
        PeliculaDto dtoRespuesta;
        dtoRespuesta = peliculaServicio.insertInto(dto, "");
       
        if (dtoRespuesta!=null) {
            correcto = true;
            
        }
        return correcto;
    
    
    
    }

}

