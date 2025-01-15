package org.example.travelagency.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.travelagency.Manager.ViajeManager;
import org.example.travelagency.Viaje;

public class ViajeCon {

    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtPrecio;

    @FXML
    private Button Aceptar;
    @FXML
    private Button Cancelar;

    @FXML
    private void agregarViaje() {
        String ciudad = txtCiudad.getText();
        String pais = txtPais.getText();
        String precioStr = txtPrecio.getText();

        if (!ciudad.isEmpty() && !pais.isEmpty() && !precioStr.isEmpty()) {
            try {
                int precio = Integer.parseInt(precioStr);
                Viaje viaje = new Viaje(ciudad, pais, precio);
                ViajeManager.insertViaje(viaje);
                volver();
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El precio debe ser un número válido.");
            }
        } else {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
        }
    }

    @FXML
    private void volver() {
        try {
            // Cargar la vista principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viajes.fxml"));
            Parent root = loader.load();

            // Obtener la escena actual y reemplazarla con la nueva
            Stage stage = (Stage) Cancelar.getScene().getWindow();
            stage.setTitle("Viajes");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la página principal.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

