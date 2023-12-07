package com.example.javafx2;

import java.util.ArrayList;

public class Bruger {

    private String brugerLogin;
    private String password;
    private String fNavn;
    private String eNavn;
    private String mobil;
    private String email;
    private ArrayList<Oenske> oenskeliste = new ArrayList<>();



    public void tilfoejoenske(Oenske oenske) {
        oenskeliste.add(oenske);
    }

    public ArrayList<Oenske> getOenskeliste(Bruger bruger1) {
        return bruger1.oenskeliste;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Oenske> getOenskeliste() {
        return oenskeliste;
    }

    public void setOenskeliste(ArrayList<Oenske> oenskeliste) {
        this.oenskeliste = oenskeliste;
    }

    public Bruger(String brugerLogin, String password, String fNavn, String eNavn, String mobil, String email) {
        this.brugerLogin = brugerLogin;
        this.password = password;
        this.fNavn = fNavn;
        this.eNavn = eNavn;
        this.mobil = mobil;
        this.email = email;
    }

    public Bruger() {
    }



    public String getBrugerLogin() {
        return brugerLogin;
    }

    public void setBrugerLogin(String brugerLogin) {
        this.brugerLogin = brugerLogin;
    }

    public String getfNavn() {
        return fNavn;
    }

    public void setfNavn(String fNavn) {
        this.fNavn = fNavn;
    }

    public String geteNavn() {
        return eNavn;
    }

    public void seteNavn(String eNavn) {
        this.eNavn = eNavn;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void udskrivOenskeliste(){
        for(int i = 0; i<oenskeliste.size();i++){
            System.out.println(brugerLogin + "Ønskeliste");
            System.out.print(i+":");
            System.out.print(oenskeliste.get(i).getoenskeNavn());
            System.out.print(", ");
            System.out.print(oenskeliste.get(i).getoenskeAntal());
            System.out.print(", ");
            System.out.print(oenskeliste.get(i).getoenskeLink());

        }

    }

    @Override
    public String toString() {
        return "com.example.javafx2.Bruger{" +
                "brugerLogin='" + brugerLogin + '\'' +
                ", password='" + password + '\'' +
                ", fNavn='" + fNavn + '\'' +
                ", eNavn='" + eNavn + '\'' +
                ", mobil='" + mobil + '\'' +
                ", email='" + email + '\'' +
                ", oenskeliste=" + oenskeliste +
                '}';
    }
}
