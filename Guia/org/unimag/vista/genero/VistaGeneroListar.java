package org.unimag.vista.genero;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.unimag.controlador.GeneroControladorListar;
import org.unimag.dto.GeneroDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;

public class VistaGeneroListar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<GeneroDto> miTabla;

    private static final String ESTILO_CENTRAR
            = "-fx-alignment: CENTER;";
    private static final String ESTILO_DERECHA
            = "-fx-alignment: CENTER-RIGHT;";
    private static final String ESTILO_IZQUIERDA
            = "-fx-alignment: CENTER-LEFT;";
    private static final String ESTILO_ROJO
            = "-fx-text-fill: red; " + ESTILO_DERECHA;
    private static final String ESTILO_VERDE
            = "-fx-text-fill: green; " + ESTILO_DERECHA;

    public VistaGeneroListar(
            Stage ventanaPadre, double ancho, double alto
    ) {
        setAlignment(Pos.CENTER);
        miEscenario = ventanaPadre;
        marco = Marco.crear(
                miEscenario,
                Configuracion.MARCO_ALTO_PORCENTAJE,
                Configuracion.MARCO_ANCHO_PORCENTAJE,
                Configuracion.DEGRADE_ARREGLO,
                Configuracion.DEGRADDE_BORDE
        );
        miTabla = new TableView<>();
        cajaVertical = new VBox(35);
        getChildren().add(marco);

        configurarCajaVertical();
        crearTitulo();
        crearTabla();

    }

    private void configurarCajaVertical() {
        cajaVertical.setAlignment(Pos.TOP_CENTER);
        cajaVertical.prefWidthProperty()
                .bind(miEscenario.widthProperty());
        cajaVertical.prefHeightProperty()
                .bind(miEscenario.heightProperty());
    }

    private void crearTitulo() {
        Region bloqueSeparador = new Region();
        bloqueSeparador.prefHeightProperty().bind(
                miEscenario.heightProperty().multiply(0.05));
        int cant = GeneroControladorListar.obtenerCantidadGeneros();
        Text titulo = new Text("Lista de géneros (" + cant + ")");
        titulo.setFill(Color.web("#de3d37"));
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<GeneroDto, Integer> crearColumnaCodigo() {
        TableColumn<GeneroDto, Integer> columna = new TableColumn<>("cod");
        columna.setCellValueFactory(new PropertyValueFactory<>("idGenero"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.10));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<GeneroDto, String> crearColumnaNombre() {
        TableColumn<GeneroDto, String> columna = new TableColumn<>("Nombre");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreGenero"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.20));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<GeneroDto, String> crearColumnaEstado() {
        TableColumn<GeneroDto, String> columna = new TableColumn<>("Estado");
        columna.setCellValueFactory(obj -> {
            String estado = obj.getValue().getEstadoGenero() ? "Activo" : "Inactivo";
            return new SimpleStringProperty(estado);   
        });

        columna.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String estadoTXT, boolean empty) {
                super.updateItem(estadoTXT, empty);
                if (empty || estadoTXT == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(estadoTXT);
                    setStyle("Activo".equals(estadoTXT) ? ESTILO_VERDE : ESTILO_ROJO );
                }
            }
            
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.07));
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaCodigo(),
                        crearColumnaNombre(),
                        crearColumnaEstado()
                )
        );
    }

    private void crearTabla() {
        configurarColumnas();

        List<GeneroDto> arrGeneros = GeneroControladorListar.obtenerGeneros();
        ObservableList<GeneroDto> datosTabla = FXCollections.observableArrayList(arrGeneros);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay géneros registrados."));

        // Evita scroll horizontal
        miTabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // Responsividad
        miTabla.maxWidthProperty().bind(miEscenario.widthProperty().multiply(0.50));
        miTabla.maxHeightProperty().bind(miEscenario.heightProperty().multiply(0.50));
        miEscenario.heightProperty().addListener((o, oldVal, newVal)
                -> miTabla.setPrefHeight(newVal.doubleValue())
        );
        VBox.setVgrow(miTabla, Priority.ALWAYS);

        cajaVertical.getChildren().add(miTabla);
        getChildren().add(cajaVertical);
    }

}

