package com.example.javafx2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.IOException;
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

public class andresOenskelisteController implements Initializable {

    DbSql db = new DbSql();

    @FXML
    private TableView<Oenske> andreOenskeListe;
    @FXML
    private TableColumn<Oenske, String> modtagerColumn;
    @FXML
    private TableColumn<Oenske, String> oenskeColumn;
    @FXML
    private TableColumn<Oenske, Integer> antalColumn;
    @FXML
    private TableColumn<Oenske, String> linkColumn;
    @FXML
    private TableColumn<Oenske, Button> koebtColumn;
    @FXML
    private TableColumn<Oenske, String> koebtAfColumn;
    @FXML
    private Button tilbageButton;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        Runnable initializeRunnable = new Runnable() {
            public void run() {
                udskrivOenskeliste();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(initializeRunnable, 0, 1, TimeUnit.SECONDS);
    }



    public void udskrivOenskeliste() {
        modtagerColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeEjer"));
        oenskeColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeNavn"));
        antalColumn.setCellValueFactory(new PropertyValueFactory<Oenske, Integer>("oenskeAntal"));
        linkColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeLink"));
        koebtColumn.setCellValueFactory(new PropertyValueFactory<Oenske,Button>("oenskeKoebtButton"));
        koebtAfColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeKoebtAf"));
        andreOenskeListe.setItems(db.seAndresOenskelister());
    }


    @FXML
    private void brugerTilbage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("afterLogin.fxml");
    }






}
