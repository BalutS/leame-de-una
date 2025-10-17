package com.leamedeuna.view;

import com.leamedeuna.controller.LibroController;
import com.leamedeuna.dto.LibroDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.text.DecimalFormat;
import java.util.List;

public class LibroTableView extends VBox {

    private final TableView<LibroDTO> tableView;

    public LibroTableView() {
        this.tableView = new TableView<>();
        setSpacing(10);
        setPadding(new Insets(10));
        configurarTabla();
        cargarDatos();
        getChildren().add(tableView);
    }

    private void configurarTabla() {
        TableColumn<LibroDTO, Integer> idCol = new TableColumn<>("Código");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<LibroDTO, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<LibroDTO, String> precioCol = new TableColumn<>("Precio");
        precioCol.setCellValueFactory(cellData -> {
            double precio = cellData.getValue().getPrecio();
            DecimalFormat df = new DecimalFormat("#,##0.00");
            return new SimpleStringProperty(df.format(precio));
        });

        TableColumn<LibroDTO, Integer> anioCol = new TableColumn<>("Año");
        anioCol.setCellValueFactory(new PropertyValueFactory<>("anio"));

        TableColumn<LibroDTO, String> editorialCol = new TableColumn<>("Editorial");
        editorialCol.setCellValueFactory(cellData -> {
            String editorialInfo = cellData.getValue().getEditorial().getNombre() + " (" + cellData.getValue().getEditorial().getPais() + ")";
            return new SimpleStringProperty(editorialInfo);
        });

        TableColumn<LibroDTO, String> autorCol = new TableColumn<>("Autor");
        autorCol.setCellValueFactory(cellData -> {
            String genero = "masculino".equalsIgnoreCase(cellData.getValue().getAutor().getGenero()) ? "Masculino" : "Femenino";
            String autorInfo = cellData.getValue().getAutor().getNombre() + " (" + genero + ")";
            return new SimpleStringProperty(autorInfo);
        });

        tableView.getColumns().addAll(idCol, nombreCol, precioCol, anioCol, editorialCol, autorCol);
    }

    private void cargarDatos() {
        List<LibroDTO> libros = LibroController.getAllLibros();
        ObservableList<LibroDTO> data = FXCollections.observableArrayList(libros);
        tableView.setItems(data);
    }
}