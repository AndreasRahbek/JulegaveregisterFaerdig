package com.example.javafx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class StartMenuController {
    DbSql db = new DbSql();
    public StartMenuController() {

    }

    @FXML
    private Button loginButton;
    private Button opretBrugerButton;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField brugerIdPanel;
    @FXML
    private PasswordField passwordPanel;



    public void opretBruger(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("opretBruger.fxml");
    }



    public void brugerLogin(ActionEvent event) throws IOException, SQLException {
        Main m = new Main();
        if (brugerIdPanel.getText().isEmpty() && passwordPanel.getText().isEmpty()) {
            wrongLogin.setText("Indtast dit BrugerId og Password ");
        } else if(db.checkLogin(brugerIdPanel.getText(),passwordPanel.getText())){
                m.changeScene("afterLogin.fxml");
        } else {wrongLogin.setText("Forkert login");
        }
    }
}










