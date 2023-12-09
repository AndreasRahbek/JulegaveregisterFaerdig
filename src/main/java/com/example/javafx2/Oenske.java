package com.example.javafx2;

public class Oenske {
    private int oenskeId;
    private String oenskeNavn;
    private int oenskeAntal;
    private String oenskeLink;
    private boolean oenskeKoebt = false;
    private String oenskeKoebtAf;



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

    public boolean isOenskeKøbt() {
        return oenskeKoebt;
    }

    public void setOenskeKøbt(boolean oenskeKøbt) {
        this.oenskeKoebt = oenskeKøbt;
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
