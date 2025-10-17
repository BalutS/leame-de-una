package org.unimag.controlador;

import org.unimag.dto.GeneroDto;
import org.unimag.servicio.GeneroServicio;

public class GeneroControladorGrabar {
    
    public static Boolean crearGenero (GeneroDto dto){
        Boolean correcto = false;
        
        GeneroServicio generoServicio = new GeneroServicio();
        GeneroDto dtoRespuesta;
        dtoRespuesta = generoServicio.insertInto(dto, "");
        if (dtoRespuesta!=null) {
            correcto = true;
            
        }
        return correcto;
    
    
    
    }

}

