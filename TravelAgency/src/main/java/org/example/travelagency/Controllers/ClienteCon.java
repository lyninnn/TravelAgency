package org.example.travelagency.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public void initialize() {
        mostrarViajes(); // Llenar ComboBox al inicializar
    }

    @FXML
    private void agregarCliente() {
        // ID del cliente (solo para modificar)
        int id = -1; // Valor predeterminado para identificar nuevos clientes

        if (Aceptar.getText().equals("Modificar")) {
            // Recuperar el ID del cliente al modificar
            id = Integer.parseInt(Aceptar.getUserData().toString());
        }

        String nombre = txtNombre.getText();
        String nacionalidad = txtNacionalidad.getText();
        Viaje viajeSeleccionado = cbViaje.getValue();

        if (!nombre.isEmpty() && !nacionalidad.isEmpty() && viajeSeleccionado != null) {
            if (Aceptar.getText().equals("Modificar")) {
                // Lógica para actualizar el cliente
                Cliente cliente = new Cliente(id, nombre, nacionalidad, viajeSeleccionado);
                ClienteManager.updateCliente(cliente);
                volver();
            } else {
                // Lógica para insertar un nuevo cliente
                Cliente cliente = new Cliente(nombre, nacionalidad, viajeSeleccionado);
                ClienteManager.insertCliente(cliente);
                volver();
            }
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
        if(Cancelar.getText().equals("Volver")){
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/travelagency/inicio.fxml"));
            Parent root = loader.load();

            // Obtener la escena actual y reemplazarla con la nueva
            Stage stage = (Stage) Cancelar.getScene().getWindow();
            stage.setTitle("Clientes");
            stage.setScene(new Scene(root));
            stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo abrir la página de registro.");
            }
        }else {
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

    }

    public void rellenar(Cliente cliente) {
        txtNombre.setText(cliente.getNombre());
        txtNacionalidad.setText(cliente.getNacionalidad());
        cbViaje.setValue(cliente.getViaje());
        Aceptar.setText("Modificar");
        Aceptar.setUserData(cliente.getId()); // Guardar el ID del cliente en el botón
        Cancelar.setText("Volver");
    }




    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
