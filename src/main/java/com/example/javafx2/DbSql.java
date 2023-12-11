package com.example.javafx2;

import java.sql.*;
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
            String url = "jdbc:sqlite:\\C:\\Users\\Andreas\\Documents\\1. semester\\Databaser\\JuleregisterDatabase.db\\";
            connection = DriverManager.getConnection(url);

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretBruger(String brugerlogin, String password, String fnavn, String enavn, String mobil, String email) {
        try {
            String sql = "insert into Bruger(brugerlogin,password,fNavn,enavn,mobil,email) values(" + "'" + brugerlogin + "','" + password + "','" + fnavn + "','" + enavn + "','" + mobil + "','" + email + "')";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Bruger getBruger(String brugerLogin){
        Bruger bruger = new Bruger();
        try {
            String sql = "SELECT * from Bruger where brugerlogin = '" + brugerLogin +"';";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                bruger.setBrugerLogin(rs.getString("brugerlogin"));
                bruger.setPassword(rs.getString("password"));
                bruger.setfNavn(rs.getString("fnavn"));
                bruger.seteNavn(rs.getString("enavn"));
                bruger.setMobil(rs.getString("mobil"));
                bruger.setEmail(rs.getString("email"));
                statement.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bruger;
    }

    public void login(String brugerLogin, String brugerpassword) {
        try {
            String sql = "SELECT brugerlogin, password" + " from Bruger" + " WHERE brugerlogin =" + " '" + brugerLogin + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (Objects.equals(rs.getString("brugerlogin"), brugerLogin) && (Objects.equals(rs.getString("password"), brugerpassword))) {
                System.out.println("login succesful");
                this.login = brugerLogin;
                this.password = brugerpassword;
            } else {
                System.out.println("Username or password incorrect");
            }
            statement.execute(sql);
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void opretOenske(String oenskeNavn, int oenskeAntal, String oenskeLink) {
        try {
            String sql = "insert into Oenske(brugerlogin,navn,antal,link) values(" + "'" + this.login + "','" + oenskeNavn + "','" + oenskeAntal + "','" + oenskeLink + "')";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void udskrivOenskeliste(String brugerLogin) {
        Bruger bruger = getBruger(brugerLogin);
        try {
            String sql = "SELECT Oenske.navn, Oenske.antal, Oenske.link from Oenske where brugerlogin ='" + brugerLogin + "';";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Oenske oenske = new Oenske();
                oenske.setOenskeNavn(rs.getString("navn"));
                oenske.setOenskeAntal(rs.getInt("antal"));
                oenske.setOenskeLink(rs.getString("link"));
                bruger.tilfoejoenske(oenske);
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        bruger.udskrivOenskeliste();


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


