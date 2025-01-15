package org.example.travelagency.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.example.travelagency.Cliente;
import org.example.travelagency.Manager.ClienteManager;
import org.example.travelagency.Manager.ViajeManager;
import org.example.travelagency.Viaje;

import java.time.LocalDate;

public class ClienteCon {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private ComboBox<Viaje> cbViaje;

    @FXML
    private Button Aceptar;
    @FXML
    private Button Cancelar;




    @FXML
    private void agregarCliente() {
        String nombre = txtNombre.getText();
        String nacionalidad = txtNacionalidad.getText();
        Viaje viajeSeleccionado = cbViaje.getValue();

        if (!nombre.isEmpty() && !nacionalidad.isEmpty() && viajeSeleccionado != null) {
            Cliente cliente = new Cliente(nombre, nacionalidad, viajeSeleccionado);
            ClienteManager.insertCliente(cliente);
            volver();
        } else {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
        }
    }

    @FXML
    void mostrarViajes() {
        try {
            // Obtener la lista de viajes desde el gestor
            ObservableList<Viaje> viajes = FXCollections.observableArrayList(ViajeManager.getViaje());

            // Llenar el ComboBox con los datos
            cbViaje.setItems(viajes);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudieron cargar los viajes.");
        }
    }

    @FXML
    private void volver() {
        try {
            // Cargar la nueva vista de registro
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/travelagency/main.fxml"));
            Parent root = loader.load();

            // Obtener la escena actual y reemplazarla con la nueva
            Stage stage = (Stage) Cancelar.getScene().getWindow();
            stage.setTitle("TravelAgency");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la página de registro.");
        }
    }



    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
