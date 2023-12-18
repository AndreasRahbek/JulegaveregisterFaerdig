package com.example.javafx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class afterLoginController {

    @FXML
    private Button logUdButton;
    @FXML
    private Button seOenskeliste;
    @FXML
    private Button andreOenskeLister;


    @FXML
    private void userLogOut(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("StartMenu.fxml");
    }
    @FXML
    private void seOenskeliste(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("seOenskeListe.fxml");
    }
    @FXML
    private void andresOenskeLister(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("seAndresOenskeliste.fxml");
    }
}
