package com.example.javafx2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private TableColumn <Oenske, String> linkColumn;
    @FXML
    private TableColumn <Oenske, Boolean> koebtColumn;
    @FXML
    private TableColumn <Oenske, String> koebtAfColumn;

    //12-9-2023




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
    private ObservableList<Oenske> getOenskeliste(String brugerLogin) {
        Connection connection = db.getConnection();
        ObservableList<Oenske> oensker = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Oenske.navn, Oenske.antal, Oenske.link from Oenske where brugerlogin ='" + brugerLogin + "';";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Oenske oenske = new Oenske();
                oenske.setOenskeNavn(rs.getString("navn"));
                oenske.setOenskeAntal(rs.getInt("antal"));
                oenske.setOenskeLink(rs.getString("link"));
                oensker.add(oenske);
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return oensker;
    }


    public void udskrivOenskeliste(){
        oenskeColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeNavn"));
        antalColumn.setCellValueFactory(new PropertyValueFactory<Oenske, Integer>("oenskeAntal"));
        linkColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeLink"));
        andreOenskeListe.setItems(getOenskeliste(db.getLogin()));
    }




}

