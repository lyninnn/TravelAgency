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
import org.example.travelagency.Manager.ClienteManager;
import org.example.travelagency.Manager.ViajeManager;
import org.example.travelagency.Viaje;

import java.io.IOException;
import java.time.LocalDate;

public class Clientes {
    @FXML
    private TableView<Cliente> tableClientes;
    @FXML
    private TableColumn<Cliente, Integer> colId;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colNacionalidad;
    @FXML
    private TableColumn<Cliente, LocalDate> colFechaRegistro;
    @FXML
    private TableColumn<Cliente, String> colViaje;


    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;

    private ObservableList<Cliente> clientesList;
    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        cargarClientes();

    }

    private void cargarClientes() {
        clientesList = FXCollections.observableArrayList(ClienteManager.getCliente());
        tableClientes.setItems(clientesList);
    }

    @FXML
    private void agregarCliente() {
        try {
            // Cargar la vista de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cliente.fxml"));
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
    private void actualizarCliente() {
        Cliente clienteSeleccionado = tableClientes.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado != null) {
            clienteSeleccionado.setNombre(txtNombre.getText());
            clienteSeleccionado.setNacionalidad(txtNacionalidad.getText());
            clienteSeleccionado.setViaje(cbViaje.getValue());

            ClienteManager.updateCliente(clienteSeleccionado);
            cargarClientes();
        } else {
            mostrarAlerta("Error", "Seleccione un cliente para actualizar.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarCliente() {
        Cliente clienteSeleccionado = tableClientes.getSelectionModel().getSelectedItem();

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

