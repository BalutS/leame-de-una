package org.unimag.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.unimag.dto.AutorDTO;
import org.unimag.dto.EditorialDTO;
import org.unimag.dto.LibroDTO;
import org.unimag.modelo.Autor;
import org.unimag.modelo.Editorial;
import org.unimag.modelo.Libro;
import org.unimag.servicio.AutorServicio;
import org.unimag.servicio.EditorialServicio;
import org.unimag.servicio.LibroServicio;

public class DashboardController implements Initializable {

    // Services
    private final AutorServicio autorServicio = new AutorServicio();
    private final EditorialServicio editorialServicio = new EditorialServicio();
    private final LibroServicio libroServicio = new LibroServicio();

    // Book form
    @FXML
    private TextField nombreLibroField;
    @FXML
    private TextField precioLibroField;
    @FXML
    private TextField anioLibroField;
    @FXML
    private ComboBox<Autor> autorComboBox;
    @FXML
    private ComboBox<Editorial> editorialComboBox;
    @FXML
    private Button crearLibroButton;

    // Books table
    @FXML
    private TableView<LibroDTO> librosTable;
    @FXML
    private TableColumn<LibroDTO, String> libroIdColumn;
    @FXML
    private TableColumn<LibroDTO, String> libroNombreColumn;
    @FXML
    private TableColumn<LibroDTO, String> libroPrecioColumn;
    @FXML
    private TableColumn<LibroDTO, Integer> libroAnioColumn;
    @FXML
    private TableColumn<LibroDTO, String> libroEditorialColumn;
    @FXML
    private TableColumn<LibroDTO, String> libroAutorColumn;

    // Authors table
    @FXML
    private TableView<AutorDTO> autoresTable;
    @FXML
    private TableColumn<AutorDTO, String> autorIdColumn;
    @FXML
    private TableColumn<AutorDTO, String> autorNombreColumn;
    @FXML
    private TableColumn<AutorDTO, String> autorGeneroColumn;
    @FXML
    private TableColumn<AutorDTO, Integer> autorLibrosColumn;

    // Editorials table
    @FXML
    private TableView<EditorialDTO> editorialesTable;
    @FXML
    private TableColumn<EditorialDTO, String> editorialIdColumn;
    @FXML
    private TableColumn<EditorialDTO, String> editorialNombreColumn;
    @FXML
    private TableColumn<EditorialDTO, String> editorialPaisColumn;
    @FXML
    private TableColumn<EditorialDTO, String> editorialFormatoColumn;
    @FXML
    private TableColumn<EditorialDTO, Integer> editorialLibrosColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize tables
        setupLibrosTable();
        setupAutoresTable();
        setupEditorialesTable();

        // Load data
        cargarLibros();
        cargarAutores();
        cargarEditoriales();

        // Load combo boxes
        cargarAutoresComboBox();
        cargarEditorialesComboBox();
    }

    private void setupLibrosTable() {
        libroIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        libroNombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        libroPrecioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        libroAnioColumn.setCellValueFactory(new PropertyValueFactory<>("anio"));
        libroEditorialColumn.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        libroAutorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
    }

    private void setupAutoresTable() {
        autorIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        autorNombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        autorGeneroColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
        autorLibrosColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadLibros"));
    }

    private void setupEditorialesTable() {
        editorialIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        editorialNombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        editorialPaisColumn.setCellValueFactory(new PropertyValueFactory<>("pais"));
        editorialFormatoColumn.setCellValueFactory(new PropertyValueFactory<>("formato"));
        editorialLibrosColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadLibros"));
    }

    private void cargarLibros() {
        List<Libro> libros = libroServicio.selectFrom();
        List<LibroDTO> libroDTOs = libros.stream()
                .map(libro -> new LibroDTO(
                        String.valueOf(libro.getIdLibro()),
                        libro.getNombreLibro(),
                        libro.getPrecioLibro(),
                        libro.getAnioLibro(),
                        libro.getIdEditorial().getNombreEditorial(),
                        libro.getIdEditorial().getPaisEditorial(),
                        libro.getIdAutor().getNombreAutor(),
                        String.valueOf(libro.getIdAutor().getGeneroAutor())))
                .collect(Collectors.toList());
        librosTable.setItems(FXCollections.observableArrayList(libroDTOs));
    }

    private void cargarAutores() {
        List<Autor> autores = autorServicio.selectFrom();
        List<Libro> libros = libroServicio.selectFrom();
        List<AutorDTO> autorDTOs = autores.stream()
                .map(autor -> {
                    int cantidadLibros = (int) libros.stream().filter(libro -> libro.getIdAutor().getIdAutor().equals(autor.getIdAutor())).count();
                    return new AutorDTO(
                            String.valueOf(autor.getIdAutor()),
                            autor.getNombreAutor(),
                            String.valueOf(autor.getGeneroAutor()),
                            cantidadLibros);
                })
                .collect(Collectors.toList());
        autoresTable.setItems(FXCollections.observableArrayList(autorDTOs));
    }

    private void cargarEditoriales() {
        List<Editorial> editoriales = editorialServicio.selectFrom();
        List<Libro> libros = libroServicio.selectFrom();
        List<EditorialDTO> editorialDTOs = editoriales.stream()
                .map(editorial -> {
                    int cantidadLibros = (int) libros.stream().filter(libro -> libro.getIdEditorial().getIdEditorial().equals(editorial.getIdEditorial())).count();
                    return new EditorialDTO(
                            String.valueOf(editorial.getIdEditorial()),
                            editorial.getNombreEditorial(),
                            editorial.getPaisEditorial(),
                            String.valueOf(editorial.getFormatoEditorial()),
                            cantidadLibros);
                })
                .collect(Collectors.toList());
        editorialesTable.setItems(FXCollections.observableArrayList(editorialDTOs));
    }

    private void cargarAutoresComboBox() {
        autorComboBox.setItems(FXCollections.observableArrayList(autorServicio.selectFrom()));
    }

    private void cargarEditorialesComboBox() {
        editorialComboBox.setItems(FXCollections.observableArrayList(editorialServicio.selectFrom()));
    }

    @FXML
    private void crearLibro(ActionEvent event) {
        String nombre = nombreLibroField.getText();
        Double precio = Double.parseDouble(precioLibroField.getText());
        Short anio = Short.parseShort(anioLibroField.getText());
        Autor autor = autorComboBox.getValue();
        Editorial editorial = editorialComboBox.getValue();

        Libro libro = new Libro();
        libro.setNombreLibro(nombre);
        libro.setPrecioLibro(precio);
        libro.setAnioLibro(anio);
        libro.setIdAutor(autor);
        libro.setIdEditorial(editorial);

        libroServicio.insertInto(libro, null);

        cargarLibros();
        cargarAutores();
        cargarEditoriales();

        nombreLibroField.clear();
        precioLibroField.clear();
        anioLibroField.clear();
    }
}