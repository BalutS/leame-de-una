
package org.unimag.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.unimag.controlador.SalidaControlador;
import org.unimag.vista.gestor.VistaAdmin;

public class Principal extends Application{
    
    private VistaAdmin vistaAdmin;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        vistaAdmin = new VistaAdmin(stage);
        stage.setTitle("dios mio que sirva");
        
        vistaAdmin.habilitarXCerrar(()->{
            SalidaControlador.verificar(stage);
        });
        
        
        stage.show();
    }
}

