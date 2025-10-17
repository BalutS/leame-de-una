package com.leamedeuna.view;

import com.leamedeuna.controller.EditorialController;
import com.leamedeuna.dto.EditorialDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.util.List;

public class EditorialTableView extends VBox {

    private final TableView<EditorialDTO> tableView;

    public EditorialTableView() {
        this.tableView = new TableView<>();
        setSpacing(10);
        setPadding(new Insets(10));
        configurarTabla();
        cargarDatos();
        getChildren().add(tableView);
    }

    private void configurarTabla() {
        TableColumn<EditorialDTO, Integer> idCol = new TableColumn<>("Código");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<EditorialDTO, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<EditorialDTO, String> paisCol = new TableColumn<>("País");
        paisCol.setCellValueFactory(new PropertyValueFactory<>("pais"));

        TableColumn<EditorialDTO, String> formatoCol = new TableColumn<>("Formato");
        formatoCol.setCellValueFactory(cellData -> {
            String formato;
            switch (cellData.getValue().getFormato()) {
                case 1:
                    formato = "Impreso";
                    break;
                case 2:
                    formato = "Digital";
                    break;
                case 3:
                    formato = "Impreso y Digital";
                    break;
                default:
                    formato = "Desconocido";
            }
            return new SimpleStringProperty(formato);
        });

        TableColumn<EditorialDTO, Integer> librosCol = new TableColumn<>("Cantidad de Libros");
        librosCol.setCellValueFactory(new PropertyValueFactory<>("cantidadLibros"));

        tableView.getColumns().addAll(idCol, nombreCol, paisCol, formatoCol, librosCol);
    }

    private void cargarDatos() {
        List<EditorialDTO> editoriales = EditorialController.getAllEditoriales();
        ObservableList<EditorialDTO> data = FXCollections.observableArrayList(editoriales);
        tableView.setItems(data);
    }
}