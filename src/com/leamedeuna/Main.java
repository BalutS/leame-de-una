package com.leamedeuna;

import com.leamedeuna.view.AutorTableView;
import com.leamedeuna.view.EditorialTableView;
import com.leamedeuna.view.LibroFormView;
import com.leamedeuna.view.LibroTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab libroFormTab = new Tab("Crear Libro");
        libroFormTab.setContent(new LibroFormView(primaryStage, 800, 600));

        Tab libroListTab = new Tab("Listado de Libros");
        libroListTab.setContent(new LibroTableView());

        Tab editorialListTab = new Tab("Listado de Editoriales");
        editorialListTab.setContent(new EditorialTableView());

        Tab autorListTab = new Tab("Listado de Autores");
        autorListTab.setContent(new AutorTableView());

        tabPane.getTabs().addAll(libroFormTab, libroListTab, editorialListTab, autorListTab);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("Léame de una - Gestión de Libros");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}