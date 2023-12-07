package com.example.javafx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogIn {

    public LogIn() {

    }

    @FXML
    private Button loginButton;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField brugerIdPanel;
    @FXML
    private PasswordField passwordPanel;

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }
    private void checkLogin() throws IOException {
        HelloApplication m = new HelloApplication();
        if (brugerIdPanel.getText().toString().equals("brugerId") && passwordPanel.getText().toString().equals("123")) {
            wrongLogin.setText("Success!");

            m.changeScene("afterLogin.fxml");

        } else if (brugerIdPanel.getText().isEmpty() && passwordPanel.getText().isEmpty()) {

            wrongLogin.setText("Indtast dit BrugerId og Password ");
        }
        else wrongLogin.setText("Forkert login ");

    }
}

