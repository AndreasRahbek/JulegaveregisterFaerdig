package com.example.javafx2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML
    private Button seKoebteButton;
    @FXML
    private Button seIkkeKoebteButton;
    @FXML
    private Button seAlleButton;




    public void initialize(URL url, ResourceBundle resourceBundle) {
        udskrivOenskelisteAlle();

    }









    public void udskrivOenskelisteAlle() {
        modtagerColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeEjer"));
        oenskeColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeNavn"));
        antalColumn.setCellValueFactory(new PropertyValueFactory<Oenske, Integer>("oenskeAntal"));
        linkColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeLink"));
        koebtColumn.setCellValueFactory(new PropertyValueFactory<Oenske,Button>("oenskeKoebtButton"));
        koebtAfColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeKoebtAf"));
        andreOenskeListe.setItems(db.getOenskeListeAlleDelte());
    }

    @FXML
    public void udskrivOenskelisteKoebte(){
        ObservableList<Oenske> oensker = FXCollections.observableArrayList();
        andreOenskeListe.setItems(oensker);
        modtagerColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeEjer"));
        oenskeColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeNavn"));
        antalColumn.setCellValueFactory(new PropertyValueFactory<Oenske, Integer>("oenskeAntal"));
        linkColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeLink"));
        koebtColumn.setCellValueFactory(new PropertyValueFactory<Oenske,Button>("fjernKoebtButton"));
        koebtAfColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeKoebtAf"));
        andreOenskeListe.setItems(db.getOenskeListeKoebte());

    }

    @FXML
    public void udskrivOenskelisteIkkeKoebte(){
        ObservableList<Oenske> oensker = FXCollections.observableArrayList();
        andreOenskeListe.setItems(oensker);
        modtagerColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeEjer"));
        oenskeColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeNavn"));
        antalColumn.setCellValueFactory(new PropertyValueFactory<Oenske, Integer>("oenskeAntal"));
        linkColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeLink"));
        koebtColumn.setCellValueFactory(new PropertyValueFactory<Oenske,Button>("oenskeKoebtButton"));
        koebtAfColumn.setCellValueFactory(new PropertyValueFactory<Oenske, String>("oenskeKoebtAf"));
        andreOenskeListe.setItems(db.getOenskeListeIkkeKoebte());

    }





    @FXML
    private void brugerTilbage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("afterLogin.fxml");
    }






}
