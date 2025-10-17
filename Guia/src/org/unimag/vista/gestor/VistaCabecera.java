package org.unimag.vista.gestor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.unimag.controlador.GeneroVistaControlador;
import org.unimag.controlador.PeliculaVistaControlador;
import org.unimag.recurso.constante.Configuracion;

public class VistaCabecera extends HBox {

    private final int menuAncho = 150;
    private final int menuAlto = 35;
    private final int menuEspacio = 10;

    private final Stage miEscenario;
    private final BorderPane miPanelPrincipal;

    public VistaCabecera(Stage esce, BorderPane bpan, double altoCabecera) {
        miEscenario = esce;
        miPanelPrincipal = bpan;
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(menuEspacio);
        setPadding(new Insets(0, 20, 0, 20));
        setPrefHeight(altoCabecera);
        setStyle(Configuracion.CABECERA_ESTILO_FONDO);

        crearMenus();

    }

    private void agregarBotonMenu(MenuButton miMenu) {
        miMenu.setCursor(Cursor.HAND);
        miMenu.setPrefWidth(menuAncho);
        miMenu.setPrefHeight(menuAlto);
        getChildren().add(miMenu);
    }

    private void crearMenus() {
        menuGenero();
        menuPelicula();
        menuSalas();
        menuBoletas();
    }

    private void menuGenero() {
        MenuItem opcion1 = new MenuItem("Crear género");
        MenuItem opcion2 = new MenuItem("Listar género");
        MenuItem opcion3 = new MenuItem("Administrar género");
        MenuItem opcion4 = new MenuItem("Carrusel");

        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    GeneroVistaControlador.crearGenero(
                            miEscenario,
                            Configuracion.ANCHO_APP,
                            Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    GeneroVistaControlador.listarGenero(
                            miEscenario,
                            Configuracion.ANCHO_APP,
                            Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            System.out.println("abrir administrar genero");
        });

        opcion4.setOnAction((e) -> {
            System.out.println("abrir formulario carrusel genero");
        });

        MenuButton miBoton = new MenuButton("Géneros");
        miBoton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarBotonMenu(miBoton);

    }

    private void menuPelicula() {
        MenuItem opcion1 = new MenuItem("Crear pelicula");
        MenuItem opcion2 = new MenuItem("Listar pelicula");
        MenuItem opcion3 = new MenuItem("Administrar pelicula");
        MenuItem opcion4 = new MenuItem("Carrusel pelicula");

        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    PeliculaVistaControlador.crearPelicula(
                            miEscenario,
                            Configuracion.ANCHO_APP,
                            Configuracion.ALTO_CABECERA)
            );

        });

        opcion2.setOnAction((e) -> {
            System.out.println("abrir formulario listar pelicula");
        });

        opcion3.setOnAction((e) -> {
            System.out.println("abrir administrar pelicula");
        });

        opcion4.setOnAction((e) -> {
            System.out.println("abrir formulario carrusel pelicula");
        });

        MenuButton miBoton = new MenuButton("Pelicula");
        miBoton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarBotonMenu(miBoton);

    }

    private void menuSalas() {
        MenuItem opcion1 = new MenuItem("Crear sala");
        MenuItem opcion2 = new MenuItem("Listar salas");
        MenuItem opcion3 = new MenuItem("Administrar salas");
        MenuItem opcion4 = new MenuItem("Carrusel");

        opcion1.setOnAction((e) -> {
            System.out.println("abrir formulario crear sala");
        });

        opcion2.setOnAction((e) -> {
            System.out.println("abrir formulario listar salas");
        });

        opcion3.setOnAction((e) -> {
            System.out.println("abrir administrar salas");
        });

        opcion4.setOnAction((e) -> {
            System.out.println("abrir formulario carrusel salas");
        });

        MenuButton miBoton = new MenuButton("Salas");
        miBoton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarBotonMenu(miBoton);

    }

    private void menuBoletas() {
        MenuItem opcion1 = new MenuItem("Crear boleta");
        MenuItem opcion2 = new MenuItem("Listar boletas");
        MenuItem opcion3 = new MenuItem("Administrar boletas");
        MenuItem opcion4 = new MenuItem("Carrusel");

        opcion1.setOnAction((e) -> {
            System.out.println("abrir formulario crear boleta");
        });

        opcion2.setOnAction((e) -> {
            System.out.println("abrir formulario listar boletas");
        });

        opcion3.setOnAction((e) -> {
            System.out.println("abrir administrar boletas");
        });

        opcion4.setOnAction((e) -> {
            System.out.println("abrir formulario carrusel boletas");
        });

        MenuButton miBoton = new MenuButton("Boletas");
        miBoton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarBotonMenu(miBoton);

    }

}

