package com.example.javafx2;

import javafx.scene.control.CheckBox;

public class Oenske {
    private int oenskeId;
    private String oenskeEjer;
    private String oenskeNavn;
    private int oenskeAntal;
    private String oenskeLink;
    private CheckBox oenskeKoebt;
    private String oenskeKoebtAf;
    private String oenskeDeltMed;

    public CheckBox getOenskeKoebt() {
        return oenskeKoebt;
    }

    public void setOenskeKoebt(CheckBox oenskeKoebt) {
        this.oenskeKoebt = oenskeKoebt;
    }

    public String getOenskeEjer() {
        return oenskeEjer;
    }

    public void setOenskeEjer(String oenskeEjer) {
        this.oenskeEjer = oenskeEjer;
    }

    public String getOenskeKoebtAf() {
        return oenskeKoebtAf;
    }

    public void setOenskeKoebtAf(String oenskeKoebtAf) {
        this.oenskeKoebtAf = oenskeKoebtAf;
    }

    public String getOenskeDeltMed() {
        return oenskeDeltMed;
    }

    public void setOenskeDeltMed(String oenskeDeltMed) {
        this.oenskeDeltMed = oenskeDeltMed;
    }

    public int getOenskeId() {
        return oenskeId;
    }

    public void setOenskeId(int oenskeId) {
        this.oenskeId = oenskeId;
    }

    public String getOenskeNavn() {
        return oenskeNavn;
    }

    public void setOenskeNavn(String oenskeNavn) {
        this.oenskeNavn = oenskeNavn;
    }

    public int getOenskeAntal() {
        return oenskeAntal;
    }

    public void setOenskeAntal(int oenskeAntal) {
        this.oenskeAntal = oenskeAntal;
    }

    public String getOenskeLink() {
        return oenskeLink;
    }

    public void setOenskeLink(String oenskeLink) {
        this.oenskeLink = oenskeLink;
    }

    public Oenske(int oenskeId, String oenskeNavn, int oenskeAntal, String oenskeLink) {
        this.oenskeId = oenskeId;
        this.oenskeNavn = oenskeNavn;
        this.oenskeAntal = oenskeAntal;
        this.oenskeLink = oenskeLink;
    }
    public Oenske(String oenskeNavn, int oenskeAntal, String oenskeLink) {
        this.oenskeNavn = oenskeNavn;
        this.oenskeAntal = oenskeAntal;
        this.oenskeLink = oenskeLink;
    }

    public Oenske() {
    }


    @Override
    public String toString() {
        return "Oenske{" +
                "oenskeNavn='" + oenskeNavn + '\'' +
                ", oenskeAntal=" + oenskeAntal +
                ", oenskeLink='" + oenskeLink + '\'' +
                '}';
    }
}
