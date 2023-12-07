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
    TextField brugernavn;
    @FXML
    TextField password;
    @FXML
    TextField fnavn;
    @FXML
    TextField enavn;
    @FXML
    TextField mobilNr;
    @FXML
    TextField email;
    @FXML
    Button opretBrugerButton;
    @FXML
    Button goBackButton;
    @FXML
    Label brugerEksistere;

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

