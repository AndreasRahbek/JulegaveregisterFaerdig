package com.example.javafx2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DbSql {
    private Connection connection;
    private Statement statement;
    private static String login;
    private static String password;


    DbSql() {
        connection = null;
        statement = null;
        try {
            String url = "jdbc:sqlite:\\C:\\Users\\Andreas\\Documents\\1. semester\\Databaser\\DB\\JuleregisterDatabase.db";
            connection = DriverManager.getConnection(url);

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String opretBruger(String brugerlogin, String password, String fnavn, String enavn, String mobil, String email) {
        String string = "Brugernavn eksistere allerede";
        try {
            String sql = "insert into Bruger(brugerlogin,password,fNavn,enavn,mobil,email) values(" + "'" + brugerlogin + "','" + password + "','" + fnavn + "','" + enavn + "','" + mobil + "','" + email + "')";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return string = "Brugernavn eksistere allerede";
        }
        return string = "";
    }


    public ObservableList<Oenske> seAndresOenskelister() {
        ObservableList<Oenske> oensker = FXCollections.observableArrayList();
        try {
            String sql = "SELECT OenskeDeltMed.oenskeEjer,OenskeDeltMed.oenskeId,OenskeDeltMed.deltMedBruger,Oenske.navn,oenske.antal,oenske.link,oenske.købt,oenske.købtAf from OenskeDeltMed INNER JOIN Oenske on OenskeDeltMed.oenskeId = Oenske.oenskeId";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                {
                    Oenske oenske = new Oenske();
                    oenske.setOenskeEjer(rs.getString("oenskeEjer"));
                    oenske.setOenskeId(rs.getInt("oenskeId"));
                    oenske.setOenskeDeltMed(rs.getString("deltMedBruger"));
                    oenske.setOenskeNavn(rs.getString("navn"));
                    oenske.setOenskeAntal(rs.getInt("antal"));
                    oenske.setOenskeLink(rs.getString("link"));
                    oenske.setOenskeKoebtButton(new Button("Købt"));
                    oenske.getOenskeKoebtButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            oenske.setOenskeKoebtAf(login);
                            oenskeKoebtAf(login,oenske.getOenskeId());
                        }
                    });
                    oenske.setOenskeKoebtAf(rs.getString("købtAf"));

                    if (Objects.equals(oenske.getOenskeDeltMed(), login)) {
                        oensker.add(oenske);
                    }

                }
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return oensker;
    }
    public void oenskeKoebtAf(String brugerLogin, int oenskeId){
        try {
            String sql = "Update Oenske Set købtAf =" +"'" + brugerLogin + "'" + ", købt= 'Ja'"  + " WHERE oenskeId =" + oenskeId +";";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretOenske(String oenskeNavn, int oenskeAntal, String oenskeLink) throws SQLException {
        String url = "jdbc:sqlite:\\C:\\Users\\Andreas\\Documents\\1. semester\\Databaser\\DB\\JuleregisterDatabase.db";
        connection = DriverManager.getConnection(url);
        try {
            String sql = "insert into Oenske(brugerlogin,navn,antal,link) values(" +"'" + login + "','" + oenskeNavn + "'," + String.valueOf(oenskeAntal) + ",'" + oenskeLink + "')";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean checkLogin(String login, String password) throws SQLException {
        try {
            String sql = "SELECT brugerlogin, password" + " from Bruger" + " WHERE brugerlogin =" + " '" + login + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                if (Objects.equals(rs.getString("brugerlogin"), login) && (Objects.equals(rs.getString("password"), password))) {
                    System.out.println("login succesful");
                    this.login = login;
                    this.password = password;
                    statement.close();
                    return true;
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        statement.close();
        connection.close();
        return false;
    }

    public void fjernOenske(String oenskeNavn){
        try {
            String sql = "delete from Oenske WHERE Oenske.brugerlogin=" + "'" + login + "'" + "and Oenske.navn="+ "'" + oenskeNavn +"';";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public ObservableList<Oenske> getOenskeliste() {
        ObservableList<Oenske> oensker = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Oenske.oenskeId, Oenske.navn, Oenske.antal, Oenske.link from Oenske where brugerlogin ='" + login + "';";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Oenske oenske = new Oenske();
                oenske.setOenskeId(rs.getInt("oenskeId"));
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

    public void delOenskeListe(String deltMedBruger) {
        ArrayList oenskeIdListe = new ArrayList();
        try {
            String sql = "SELECT Oenske.brugerlogin,Oenske.oenskeId from Oenske WHERE Oenske.brugerlogin = '" + login + "'";
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
        for (int i = 0; i < oenskeIdListe.size(); i++) {
            try {
                String sql = "INSERT into oenskeDeltMed(oenskeEjer,oenskeId,deltMedBruger) VALUES(" + "'" + login + "'," + oenskeIdListe.get(i) + ",'" + deltMedBruger + "')";
                Statement statement = connection.createStatement();
                statement.execute(sql);
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void udskrivEgenOenske(String brugerLogin) {
        try {
            String sql = "select * from Oenske\n" + " where brugerlogin = 'brugernavn1'";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


