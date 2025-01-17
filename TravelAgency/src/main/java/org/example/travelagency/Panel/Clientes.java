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
import org.example.travelagency.Cliente;
import org.example.travelagency.Controllers.ClienteCon;
import org.example.travelagency.Manager.ClienteManager;
import org.example.travelagency.Manager.ViajeManager;
import org.example.travelagency.Viaje;

import java.io.IOException;
import java.time.LocalDate;

public class Clientes {


    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnViajes;
    @FXML
    private Button btnEliminar;
    FXMLLoader fxmlLoader = null;
    @FXML
    private ObservableList<Cliente> clientesList;

    @FXML
    private ListView<Cliente> clientesListView;
    @FXML
    public void initialize() {

        cargarClientes();


    }

    private void cargarClientes() {
        clientesList = FXCollections.observableArrayList(ClienteManager.getCliente());
        clientesListView.setItems(clientesList);
    }

    @FXML
    private void agregarCliente() {
        try {
            // Cargar la vista de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/travelagency/cliente.fxml"));
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
    private void irViajes(){
        try {
            // Cargar la vista de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/travelagency/viajes.fxml"));
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
    private void actualizarCliente(){
        Cliente clienteSeleccionado =  clientesListView.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado != null) {
            try {
                // Inicializar el FXMLLoader y cargar el archivo FXML
                fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/travelagency/cliente.fxml"));
                Parent root = fxmlLoader.load();

                // Obtener el controlador asociado al archivo FXML
                ClienteCon clienteCon = fxmlLoader.getController();

                // Pasar los datos del cliente seleccionado al controlador
                clienteCon.rellenar(clienteSeleccionado);

                // Cambiar a la nueva vista (opcional, según tu flujo de trabajo)
                Stage stage = (Stage) clientesListView.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo cargar la vista para actualizar el cliente.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Seleccione un cliente para actualizar.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarCliente() {
        Cliente clienteSeleccionado =  clientesListView.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado != null) {
            ClienteManager.deleteCliente(clienteSeleccionado);
            cargarClientes();
        } else {
            mostrarAlerta("Error", "Seleccione un cliente para eliminar.", Alert.AlertType.ERROR);
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

