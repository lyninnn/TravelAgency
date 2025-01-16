package org.example.travelagency.Panel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.example.travelagency.Controllers.ClienteCon;
import org.example.travelagency.Controllers.ViajeCon;
import org.example.travelagency.Manager.ViajeManager;
import org.example.travelagency.Viaje;

import java.io.IOException;

public class Viajes {

    @FXML
    private TableView<Viaje> tableViajes;
    @FXML
    private TableColumn<Viaje, Integer> colId;
    @FXML
    private TableColumn<Viaje, String> colCiudad;
    @FXML
    private TableColumn<Viaje, String> colPais;
    @FXML
    private TableColumn<Viaje, Integer> colPrecio;


    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;

    private ObservableList<Viaje> viajesList;
    FXMLLoader fxmlLoader = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        cargarViajes();
    }

    private void cargarViajes() {
        viajesList = FXCollections.observableArrayList(ViajeManager.getViaje());
        tableViajes.setItems(viajesList);
    }

    @FXML
    private void agregarViaje() {
        try {
            // Cargar la vista de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viaje.fxml"));
            Parent root = loader.load();

            // Obtener la escena actual y cambiarla
            Stage stage = (Stage) btnAgregar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la página de inicio.",Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void actualizarViaje() {
        Viaje viajeSeleccionado = tableViajes.getSelectionModel().getSelectedItem();

        if (viajeSeleccionado != null) {
                ViajeCon viajeCon =fxmlLoader.getController();
                viajeCon.rellenar(viajeSeleccionado);
                cargarViajes();
            } else {
                mostrarAlerta("Error", "Seleccione un cliente para actualizar.", Alert.AlertType.ERROR);
            }

    }

    @FXML
    private void eliminarViaje() {
        Viaje viajeSeleccionado = tableViajes.getSelectionModel().getSelectedItem();

        if (viajeSeleccionado != null) {
            ViajeManager.deleteViaje(viajeSeleccionado);
            cargarViajes();
        } else {
            mostrarAlerta("Error", "Seleccione un viaje para eliminar.", Alert.AlertType.ERROR);
        }
    }


    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
