package org.example.travelagency.Panel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.example.travelagency.Controllers.ViajeCon;
import org.example.travelagency.Manager.ViajeManager;
import org.example.travelagency.Viaje;

import java.io.IOException;

public class Viajes {

    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnViajes;
    @FXML
    private ObservableList<Viaje> viajesList;

    @FXML
    private ListView<Viaje> viajeListView;

    @FXML
    public void initialize() {
        cargarViajes();
    }

    private void cargarViajes() {
        viajesList = FXCollections.observableArrayList(ViajeManager.getViaje());
        viajeListView.setItems(viajesList);
    }

    @FXML
    private void agregarViaje() {
        cargarVista("/org/example/travelagency/viaje.fxml");
    }
    @FXML
    private void irClientes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/travelagency/inicio.fxml"));
            Parent root = loader.load();

            // Obtener la escena actual y cambiarla
            Stage stage = (Stage) btnAgregar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la página.", Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void actualizarViaje() {
        Viaje viajeSeleccionado = viajeListView.getSelectionModel().getSelectedItem();

        if (viajeSeleccionado != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/travelagency/viaje.fxml"));
                Parent root = fxmlLoader.load();

                // Obtener el controlador asociado a la vista de viaje
                ViajeCon viajeCon = fxmlLoader.getController();
                viajeCon.rellenar(viajeSeleccionado);

                // Cambiar la escena
                cambiarEscena(root);
            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo cargar la vista para actualizar el viaje.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Seleccione un viaje para actualizar.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarViaje() {
        Viaje viajeSeleccionado = viajeListView.getSelectionModel().getSelectedItem();

        if (viajeSeleccionado != null) {
            ViajeManager.deleteViaje(viajeSeleccionado);
            cargarViajes();
        } else {
            mostrarAlerta("Error", "Seleccione un viaje para eliminar.", Alert.AlertType.ERROR);
        }
    }

    // Método para cargar cualquier vista FXML (refactorización para evitar duplicar código)
    private void cargarVista(String archivoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(archivoFXML));
            Parent root = loader.load();

            // Obtener la escena actual y cambiarla
            Stage stage = (Stage) btnAgregar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la página.", Alert.AlertType.ERROR);
        }
    }

    // Método para cambiar la escena
    private void cambiarEscena(Parent root) {
        Stage stage = (Stage) btnAgregar.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
