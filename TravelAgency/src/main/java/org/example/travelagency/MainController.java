package org.example.travelagency;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.travelagency.Manager.ClienteManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.travelagency.Controllers.ClienteCon;

import java.io.IOException;

public class MainController {

    @FXML
    private TextField txtUsuario; // Campo para el nombre de usuario


    @FXML
    private Button btnLogin; // Botón para iniciar sesión

    @FXML
    private Button btnRegistrar; // Botón para registrar usuario

    @FXML
    public void onLogin() {
        String nombreUsuario = txtUsuario.getText().trim();

        if (nombreUsuario.isEmpty()) {
            mostrarAlerta("Error", "El nombre de usuario y la contraseña no pueden estar vacíos.");
            return;
        }

        Cliente usuario = ClienteManager.getClienteByNombre(nombreUsuario);

        if (usuario != null) {
            mostrarAlerta("Éxito", "Inicio de sesión exitoso. Bienvenido, " + usuario.getNombre() + "!");
            redirigirAInicio();
        } else {
            mostrarAlerta("Error", "Usuario o contraseña incorrectos.");
        }
    }

    private void redirigirAInicio() {
        try {
            // Cargar la vista de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Parent root = loader.load();

            // Obtener la escena actual y cambiarla
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la página de inicio.");
        }
    }

    @FXML
    public void onRegistrar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cliente.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnRegistrar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Registrar Usuario");
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
