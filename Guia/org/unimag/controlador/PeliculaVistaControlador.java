package org.unimag.controlador;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.vista.pelicula.VistaPeliculaCrear;

public class PeliculaVistaControlador {
        public static StackPane crearPelicula(Stage esce,
            double anchito, double altito) {

        return new VistaPeliculaCrear(esce, anchito, altito);

    }

}

