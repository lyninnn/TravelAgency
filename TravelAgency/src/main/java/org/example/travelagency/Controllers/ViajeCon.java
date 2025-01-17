package org.example.travelagency.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.travelagency.Cliente;
import org.example.travelagency.Manager.ClienteManager;
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
    public void initialize(){
    }
    @FXML
    private void agregarViaje() {
        int id = -1; // Valor predeterminado para identificar nuevos clientes

        if (Aceptar.getText().equals("Modificar")) {
            // Recuperar el ID del cliente al modificar
            id = Integer.parseInt(Aceptar.getUserData().toString());
        }
        String ciudad = txtCiudad.getText();
        String pais = txtPais.getText();
        String precioStr = txtPrecio.getText();

        if (!ciudad.isEmpty() && !pais.isEmpty() && !precioStr.isEmpty()) {
                    if (Aceptar.getText().equals("Modificar")) {
                        try {
                        // Lógica para actualizar el cliente
                        Viaje viaje =new Viaje(id,ciudad,pais,Integer.parseInt(precioStr));
                        ViajeManager.updateViaje(viaje);
                        volver();
                        } catch (NumberFormatException e) {
                            mostrarAlerta("Error", "El precio debe ser un número válido.");
                        }
                    }else{
                try {
                    int precio = Integer.parseInt(precioStr);
                    Viaje viaje = new Viaje(ciudad, pais, precio);
                    ViajeManager.insertViaje(viaje);
                    volver();
                } catch (NumberFormatException e) {
                    mostrarAlerta("Error", "El precio debe ser un número válido.");
                }
            }
        }
    }

    @FXML
    private void volver() {
        if (Cancelar.getText().equals("Volver")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/travelagency/viajes.fxml"));
                Parent root = loader.load();

                // Obtener la escena actual y reemplazarla con la nueva
                Stage stage = (Stage) Cancelar.getScene().getWindow();
                stage.setTitle("Viajes");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo abrir la página de registro.");
            }
        } else {
            try {
                // Cargar la vista principal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/travelagency/viajes.fxml"));
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
    }
    public void rellenar(Viaje viaje){
        txtCiudad.setText(viaje.getCiudad());

       txtPais.setText(viaje.getPais());

        txtPrecio.setText(String.valueOf(viaje.getPrecio()));
        Aceptar.setText("Modificar");
        Aceptar.setUserData(viaje.getId());
        Cancelar.setText("Volver");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

