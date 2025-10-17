package org.unimag.recurso.constante;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Screen;

public class Configuracion {

    public static final int ALTO_APP = (int) (Screen.getPrimary().getBounds().getHeight() * 0.85);
    public static final int ANCHO_APP = (int) (Screen.getPrimary().getBounds().getWidth() * 0.70);

    private static final double CABECERA_ALTO_POCERTANJE = 0.1;
    public static final double ALTO_CABECERA = CABECERA_ALTO_POCERTANJE * ALTO_APP;

    public static final String AZUL_MARINO = "#2fddf7";
    public static final String AZUL_CLARO = "#2faef7";
    public static final String AZUL_BRILLANTE = "#41b9e0";
    public static final String AZUL_MEDIO = "#2b768f";
    public static final String CABECERA_ESTILO_FONDO = String.format(
            "-fx-background-color: linear-gradient(%s,%s,%s,%s);",
            AZUL_MARINO,AZUL_CLARO,AZUL_BRILLANTE,AZUL_MEDIO);
    

    public static final double MARCO_ALTO_PORCENTAJE = 0.6;
    public static final double MARCO_ANCHO_PORCENTAJE = 0.7;
    
    public static final double GRILLA_ANCHO_PORCENTAJE = 0.5;

    
    public static final String DEGRADDE_BORDE = "#171616";
    public  static final Stop[] DEGRADE_ARREGLO = new Stop[]{
        new Stop(0.0, Color.web("#2fddf7", 0.8)),
            new Stop(0.2, Color.web("#2faef7", 0.7)),
            new Stop(0.4, Color.web("#41b9e0", 0.6)),
            new Stop(0.6, Color.web("#2b768f", 0.5))
    };

}

