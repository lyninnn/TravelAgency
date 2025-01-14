package org.example.travelagency.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.travelagency.Cliente;
import org.example.travelagency.Manager.ClienteManager;
import org.example.travelagency.Manager.ViajeManager;
import org.example.travelagency.Viaje;

import java.time.LocalDate;

public class ClienteCon {
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
    private TextField txtNombre;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private ComboBox<Viaje> cbViaje;

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
        String nombre = txtNombre.getText();
        String nacionalidad = txtNacionalidad.getText();
        Viaje viajeSeleccionado = cbViaje.getValue();

        if (!nombre.isEmpty() && !nacionalidad.isEmpty() && viajeSeleccionado != null) {
            Cliente cliente = new Cliente(nombre, nacionalidad, viajeSeleccionado);
            ClienteManager.insertCliente(cliente);
            cargarClientes();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Por favor, complete todos los campos.", Alert.AlertType.ERROR);
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
            limpiarCampos();
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
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Seleccione un cliente para eliminar.", Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtNacionalidad.clear();
        cbViaje.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
