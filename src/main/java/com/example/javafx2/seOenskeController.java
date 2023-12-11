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


    //11-12-2023 03:43

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
    private void TilfoejOenske(String oenskeNavn, String oenskeAntal, String oenskeLink){
        Connection connection = db.getConnection();
        try {
            String sql = "insert into Oenske(brugerlogin,navn,antal,link) values(" + "'" + db.getLogin() + "','" + oenskeNavn + "','" + oenskeAntal + "','" + oenskeLink + "')";
            Statement statement = connection.createStatement();
            oenskeLabel.setText("Ønske tilføjet");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                oenskeLabel.setText(" ");
            }));
            timeline.play();

            statement.execute(sql);
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        oenskeListe.setItems(getOenskeliste(db.getLogin()));
    }

    @FXML
    private void tilfoejOenskeKlik(ActionEvent event){
        if (oenskeNavn.getText().isEmpty() || oenskeAntal.getText().isEmpty() || oenskeLink.getText().isEmpty()) {
            oenskeLabel.setText("Udfyld alle felter");
        } else {
            TilfoejOenske(oenskeNavn.getText(),oenskeAntal.getText(),oenskeLink.getText());
        }
    }


    @FXML
    public void delOenskeliste() {
        if(delOenskeListeTextField.getText().isEmpty()){
            oenskeLabel.setText("Indtast et brugernavn du vil dele med!");
        }else if (Objects.equals(delOenskeListeTextField.getText(), db.getLogin())){
            oenskeLabel.setText("Du kan ikke dele med dig selv!");
        } else {
            Connection connection = db.getConnection();
            ArrayList oenskeIdListe = new ArrayList();
            try {
                String sql ="SELECT Oenske.brugerlogin,Oenske.oenskeId from Oenske WHERE Oenske.brugerlogin = '" + db.getLogin() +"'";
                 Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql);
                 while (rs.next()) {
                     int oenskeId = rs.getInt("oenskeId");
                     oenskeIdListe.add(oenskeId);
                 }
                 statement.execute(sql);
                 statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            for(int i = 0; i<oenskeIdListe.size();i++) {
                try {
                    String sql = "INSERT into oenskeDeltMed(oenskeEjer,oenskeId,deltMedBruger) VALUES(" + "'" + db.getLogin() + "'," + oenskeIdListe.get(i) + ",'" + delOenskeListeTextField.getText() + "')";
                    Statement statement = connection.createStatement();
                    statement.execute(sql);
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }


    @FXML
    private void brugerTilbage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("afterLogin.fxml");
    }

}

