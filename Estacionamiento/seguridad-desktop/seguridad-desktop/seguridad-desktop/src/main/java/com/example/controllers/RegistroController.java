package com.example.controllers;

import com.example.services.AuthService;
import com.example.vistas.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {
    @FXML private TextField txtNum;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidoP;
    @FXML private TextField txtApellidoM;
    @FXML private PasswordField txtPass;
    @FXML private PasswordField txtPassConfirm;
    @FXML private Label mensaje;
    private Stage stage;

    public void setStage(Stage stage) { this.stage = stage; }

@FXML
private void registrar() {
    try {
  
        if (txtNum.getText().isEmpty() || txtPass.getText().isEmpty() || txtNombre.getText().isEmpty()) {
            mensaje.setText("Completa los campos obligatorios");
            mensaje.setStyle("-fx-text-fill: orange;");
            return;
        }

        int num = Integer.parseInt(txtNum.getText());
        String nombre = txtNombre.getText();
        String ap = txtApellidoP.getText();
        String am = txtApellidoM.getText();
        String pass = txtPass.getText();
        String confirm = txtPassConfirm.getText();


        if (!pass.equals(confirm)) {
            mensaje.setStyle("-fx-text-fill: red;");
            mensaje.setText("Las contraseñas no coinciden");
            return;
        }

        boolean exito = AuthService.registrarUsuario(num, nombre, ap, am, pass);

        if (exito) {
            mensaje.setStyle("-fx-text-fill: green;");
            mensaje.setText("Guardia registrado con éxito");
            
            volverLogin(); 
        } else {
            
            mensaje.setStyle("-fx-text-fill: red;");
            mensaje.setText("Datos duplicados: El número o contraseña ya existen");
        }
    } catch (NumberFormatException e) {
        mensaje.setStyle("-fx-text-fill: red;");
        mensaje.setText("El número de empleado debe ser numérico");
    }
}


@FXML
private void volverLogin() {
    LoginView login = new LoginView();
    login.mostrar(stage);
}
}