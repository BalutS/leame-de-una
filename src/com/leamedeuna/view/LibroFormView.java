package com.leamedeuna.view;

import com.leamedeuna.controller.AutorController;
import com.leamedeuna.controller.EditorialController;
import com.leamedeuna.controller.LibroController;
import com.leamedeuna.dto.AutorDTO;
import com.leamedeuna.dto.EditorialDTO;
import com.leamedeuna.dto.LibroDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.util.List;

public class LibroFormView extends GridPane {

    private TextField txtNombre;
    private TextField txtPrecio;
    private TextField txtAnio;
    private ComboBox<EditorialDTO> cmbEditorial;
    private ComboBox<AutorDTO> cmbAutor;

    public LibroFormView(Stage stage, double width, double height) {
        configurarGrid(width, height);
        crearTitulo();
        crearFormulario();
    }

    private void configurarGrid(double width, double height) {
        setHgap(10);
        setVgap(20);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        col1.setHalignment(HPos.RIGHT);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
        col2.setHgrow(Priority.ALWAYS);

        getColumnConstraints().addAll(col1, col2);
    }

    private void crearTitulo() {
        Text titulo = new Text("Formulario de Creación de Libros");
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        add(titulo, 0, 0, 2, 1);
        GridPane.setHalignment(titulo, HPos.CENTER);
    }

    private void crearFormulario() {
        add(new Label("Nombre:"), 0, 1);
        txtNombre = new TextField();
        txtNombre.setPromptText("Ingrese el nombre del libro");
        add(txtNombre, 1, 1);

        add(new Label("Precio:"), 0, 2);
        txtPrecio = new TextField();
        txtPrecio.setPromptText("Ingrese el precio del libro");
        add(txtPrecio, 1, 2);

        add(new Label("Año:"), 0, 3);
        txtAnio = new TextField();
        txtAnio.setPromptText("Ingrese el año de publicación");
        add(txtAnio, 1, 3);

        add(new Label("Editorial:"), 0, 4);
        cmbEditorial = new ComboBox<>();
        cargarEditoriales();
        add(cmbEditorial, 1, 4);

        add(new Label("Autor:"), 0, 5);
        cmbAutor = new ComboBox<>();
        cargarAutores();
        add(cmbAutor, 1, 5);

        Button btnGuardar = new Button("Guardar Libro");
        btnGuardar.setMaxWidth(Double.MAX_VALUE);
        btnGuardar.setOnAction(e -> guardarLibro());
        add(btnGuardar, 1, 6);
    }

    private void cargarEditoriales() {
        List<EditorialDTO> editoriales = EditorialController.getAllEditoriales();
        ObservableList<EditorialDTO> items = FXCollections.observableArrayList(editoriales);
        cmbEditorial.setItems(items);
        cmbEditorial.setConverter(new StringConverter<>() {
            @Override
            public String toString(EditorialDTO editorial) {
                return editorial == null ? "" : editorial.getNombre();
            }

            @Override
            public EditorialDTO fromString(String string) {
                return null;
            }
        });
    }

    private void cargarAutores() {
        List<AutorDTO> autores = AutorController.getAllAutores();
        ObservableList<AutorDTO> items = FXCollections.observableArrayList(autores);
        cmbAutor.setItems(items);
        cmbAutor.setConverter(new StringConverter<>() {
            @Override
            public String toString(AutorDTO autor) {
                return autor == null ? "" : autor.getNombre();
            }

            @Override
            public AutorDTO fromString(String string) {
                return null;
            }
        });
    }

    private void guardarLibro() {
        if (!validarCampos()) {
            return;
        }

        LibroDTO libro = new LibroDTO();
        libro.setNombre(txtNombre.getText());
        libro.setPrecio(Double.parseDouble(txtPrecio.getText()));
        libro.setAnio(Integer.parseInt(txtAnio.getText()));
        libro.setEditorial(cmbEditorial.getValue());
        libro.setAutor(cmbAutor.getValue());

        if (LibroController.crearLibro(libro)) {
            mostrarAlerta("Éxito", "Libro guardado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "No se pudo guardar el libro.", Alert.AlertType.ERROR);
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty() || txtAnio.getText().isEmpty() ||
            cmbEditorial.getValue() == null || cmbAutor.getValue() == null) {
            mostrarAlerta("Campos incompletos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return false;
        }
        try {
            Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Dato inválido", "El precio debe ser un número.", Alert.AlertType.WARNING);
            return false;
        }
        try {
            Integer.parseInt(txtAnio.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Dato inválido", "El año debe ser un número.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtPrecio.clear();
        txtAnio.clear();
        cmbEditorial.getSelectionModel().clearSelection();
        cmbAutor.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}