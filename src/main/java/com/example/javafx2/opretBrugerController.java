package com.example.javafx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class opretBrugerController {
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
    private Button goBackButton;
    @FXML
    private Label brugerEksistere;

    public opretBrugerController() {
    }

    public void opretbruger(ActionEvent event){
        indsaetBruger(brugernavn.getText(),password.getText(),fnavn.getText(),enavn.getText(),mobilNr.getText(),email.getText());
    }

    private void indsaetBruger(String brugerlogin, String password, String fnavn, String enavn, String mobil, String email) {
        DbSql db = new DbSql();
        Connection connection = db.getConnection();
        try {
            String sql = "insert into Bruger(brugerlogin,password,fNavn,enavn,mobil,email) values(" + "'" + brugerlogin + "','" + password + "','" + fnavn + "','" + enavn + "','" + mobil + "','" + email + "')";
            Statement statement = connection.createStatement();
            brugerEksistere.setText("Bruger Oprettet!");
            statement.execute(sql);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            brugerEksistere.setText("Brugernavn eksistere allerede");
        }
    }
    public void userGoBack(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("StartMenu.fxml");
    }
}

