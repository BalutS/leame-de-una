package com.leamedeuna.view;

import com.leamedeuna.controller.AutorController;
import com.leamedeuna.dto.AutorDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.util.List;

public class AutorTableView extends VBox {

    private final TableView<AutorDTO> tableView;

    public AutorTableView() {
        this.tableView = new TableView<>();
        setSpacing(10);
        setPadding(new Insets(10));
        configurarTabla();
        cargarDatos();
        getChildren().add(tableView);
    }

    private void configurarTabla() {
        TableColumn<AutorDTO, Integer> idCol = new TableColumn<>("Código");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<AutorDTO, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<AutorDTO, String> generoCol = new TableColumn<>("Género");
        generoCol.setCellValueFactory(cellData -> {
            String genero = "masculino".equalsIgnoreCase(cellData.getValue().getGenero()) ? "Masculino" : "Femenino";
            return new SimpleStringProperty(genero);
        });

        TableColumn<AutorDTO, Integer> librosCol = new TableColumn<>("Cantidad de Libros");
        librosCol.setCellValueFactory(new PropertyValueFactory<>("cantidadLibros"));

        tableView.getColumns().addAll(idCol, nombreCol, generoCol, librosCol);
    }

    private void cargarDatos() {
        List<AutorDTO> autores = AutorController.getAllAutores();
        ObservableList<AutorDTO> data = FXCollections.observableArrayList(autores);
        tableView.setItems(data);
    }
}