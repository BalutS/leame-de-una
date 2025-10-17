package org.unimag.controlador;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.vista.genero.VistaGeneroCrear;
import org.unimag.vista.genero.VistaGeneroListar;

public class GeneroVistaControlador {

    public static StackPane crearGenero(Stage esce,
            double anchito, double altito) {
        return new VistaGeneroCrear(esce, anchito, altito);
    }

    public static StackPane listarGenero(Stage esce,
            double ancho, double alto) {
        return new VistaGeneroListar(esce, ancho, alto);
    }

}

