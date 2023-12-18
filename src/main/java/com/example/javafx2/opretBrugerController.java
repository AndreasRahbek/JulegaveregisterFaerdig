package com.example.javafx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class opretBrugerController {

    DbSql db = new DbSql();

    @FXML
    private TextField brugernavn;
    @FXML
    private TextField password;
    @FXML
    private TextField fnavn;
    @FXML
    private  TextField enavn;
    @FXML
    private TextField mobilNr;
    @FXML
    private TextField email;
    @FXML
    private Button opretBrugerButton;
    @FXML
    private Button tilbageButton;
    @FXML
    private Label brugerEksistere;

    public opretBrugerController() {
    }

    @FXML
    private void opretBruger(ActionEvent event){
        int tal = (db.opretBruger(brugernavn.getText(), password.getText(), fnavn.getText(), enavn.getText(), mobilNr.getText(), email.getText()));
            if(tal == 1) {
                brugerEksistere.setText("Udfyld alle felter!");
            } else if(tal == 2){
                brugerEksistere.setText("Bruger oprettet!");
            } else if(tal == 3){
                brugerEksistere.setText("Bruger eksisterer allerede!");
        }
    }

    @FXML
    private void brugerTilbage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("StartMenu.fxml");
    }
}

