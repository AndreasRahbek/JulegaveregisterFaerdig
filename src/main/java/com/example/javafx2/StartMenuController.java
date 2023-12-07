package com.example.javafx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class StartMenuController {
    DbSql db = new DbSql();
    public StartMenuController() {

    }

    @FXML
    private Button loginButton;
    private Button opretBrugerButton;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField brugerIdPanel;
    @FXML
    private PasswordField passwordPanel;



    public void opretBruger(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("opretBruger.fxml");
    }

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin(brugerIdPanel.getText(), passwordPanel.getText());
    }

    private void checkLogin(String brugerLogin, String brugerpassword) throws IOException {
        Main m = new Main();
        if (brugerIdPanel.getText().isEmpty() && passwordPanel.getText().isEmpty()) {
            wrongLogin.setText("Indtast dit BrugerId og Password ");
        } else {
            try {
                String sql = "SELECT brugerlogin, password" + " from Bruger" + " WHERE brugerlogin =" + " '" + brugerLogin + "'";
                Statement statement = db.getConnection().createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    if (Objects.equals(rs.getString("brugerlogin"), brugerLogin) && (Objects.equals(rs.getString("password"), brugerpassword))) {
                        System.out.println("login succesful");
                        db.setLogin(brugerLogin);
                        db.setPassword(brugerpassword);
                        m.changeScene("afterLogin.fxml");
                    } else {
                        wrongLogin.setText("Forkert login ");
                        System.out.println("Wrong login");
                    }
                    statement.execute(sql);
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
        }
    }
}










