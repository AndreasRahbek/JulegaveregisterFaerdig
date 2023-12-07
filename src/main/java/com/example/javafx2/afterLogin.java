package com.example.javafx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class afterLogin {

    @FXML
    private Button logUdButton;

    public void userLogOut(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("StartMenu.fxml");
    }
}
