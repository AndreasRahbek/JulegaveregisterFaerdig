package com.example.javafx2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class seOenskeController implements Initializable{
    DbSql db = new DbSql();

    @FXML
    private Button tilfoejOenske;
    @FXML
    private Button tilbage;
    @FXML
    private TextField oenskeNavn;
    @FXML
    private TextField oenskeAntal;
    @FXML
    private TextField oenskeLink;
    @FXML
    private Label oenskeLabel;
    @FXML
    private TableView<Oenske> oenskeListe;
    @FXML
    private TableColumn<Oenske, String> oenskeColumn;
    @FXML
    private TableColumn<Oenske, Integer> antalColumn;
    @FXML
    private TableColumn <Oenske, String> linkColumn;
    @FXML
    private TextField delOenskeListeTextField;
    @FXML
    private Button delButton;
    @FXML
    private Button fjernButton;
    @FXML
    private TextField oenskeNavnTextFeild;


    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Runnable initializeRunnable = new Runnable() {
            public void run() {
                udskrivOenskeliste();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(initializeRunnable, 0, 1, TimeUnit.SECONDS);
    }


    @FXML
    private void udskrivOenskeliste(){

        oenskeColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeNavn"));
        antalColumn.setCellValueFactory(new PropertyValueFactory<Oenske, Integer>("oenskeAntal"));
        linkColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeLink"));
        oenskeListe.setItems(db.getOenskelistePersonlig());
    }

    @FXML
    private void tilfoejOenskeKlik(ActionEvent e) throws SQLException{
        if (oenskeNavn.getText().isEmpty() || oenskeAntal.getText().isEmpty() || oenskeLink.getText().isEmpty()) {
            oenskeLabel.setText("Udfyld alle felter");
        } else {
            try {
                db.opretOenske(oenskeNavn.getText(), Integer.parseInt(oenskeAntal.getText()), oenskeLink.getText());
                oenskeLabel.setText("Ønske tilføjet");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                    oenskeLabel.setText(" ");
                }));
                timeline.play();
            } catch(RuntimeException a){
                    oenskeLabel.setText("Antal skal være et tal!");
            }
        }
    }

    @FXML
    private void fjernOenskeKlik(ActionEvent e){
        if (oenskeNavnTextFeild.getText().isEmpty()) {
            oenskeLabel.setText("Udfyld feltet for at fjerne et ønske!");
        } else {
         db.fjernOenske(oenskeNavnTextFeild.getText());
            oenskeLabel.setText("Ønske fjernet");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                oenskeLabel.setText(" ");
            }));
            timeline.play();
        }

    }

    @FXML
    private void delOenskeliste(ActionEvent event) {
        if (delOenskeListeTextField.getText().isEmpty()) {
            oenskeLabel.setText("Indtast et brugernavn du vil dele med!");
        } else if (Objects.equals(delOenskeListeTextField.getText(), db.getLogin())) {
            oenskeLabel.setText("Du kan ikke dele med dig selv!");
        } else {
            db.delOenskeListe(delOenskeListeTextField.getText());
            oenskeLabel.setText("Ønskeliste delt med " + "\"" + delOenskeListeTextField.getText() + "\"");
        }
    }

    @FXML
    private void brugerTilbage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("afterLogin.fxml");
    }

}

