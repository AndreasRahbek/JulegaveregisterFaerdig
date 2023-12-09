package com.example.javafx2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TableColumn<Oenske,String> antalColumn;
    @FXML
    private TableColumn <Oenske,String> linkColumn;
    @FXML
    private Button opdaterButton;


    @FXML
    private void TilfoejOenske(String oenskeNavn, String oenskeAntal, String oenskeLink){
        Connection connection = db.getConnection();
        try {
            String sql = "insert into Oenske(brugerlogin,navn,antal,link) values(" + "'" + db.getLogin() + "','" + oenskeNavn + "','" + oenskeAntal + "','" + oenskeLink + "')";
            Statement statement = connection.createStatement();
            oenskeLabel.setText("Ønske tilfoejet");
            statement.execute(sql);
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private ObservableList<Oenske> udskrivOenskeliste(String brugerLogin) {
        Connection connection = db.getConnection();
        ObservableList<Oenske> oensker = FXCollections.observableArrayList();
            try {
                String sql = "SELECT Oenske.navn, Oenske.antal, Oenske.link from Oenske where brugerlogin ='" + brugerLogin + "';";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Oenske oenske = new Oenske();
                    oenske.setoenskeNavn(rs.getString("navn"));
                    oenske.setoenskeAntal(rs.getInt("antal"));
                    oenske.setoenskeLink(rs.getString("link"));
                    oensker.add(oenske);
                }
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return oensker;
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
    private void opdaterOenskeliste(ActionEvent event){
        udskrivOenskeliste(db.getLogin());
    }

    @FXML
    private void userGoBack(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("StartMenu.fxml");
    }

    @FXML
    private void getTableData() {
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oenskeColumn.setCellValueFactory(new PropertyValueFactory<>("oenske"));
        antalColumn.setCellValueFactory(new PropertyValueFactory<>("antal"));
        linkColumn.setCellValueFactory(new PropertyValueFactory<>("link"));
        oenskeListe.setItems(udskrivOenskeliste(db.getLogin()));
    }
}

