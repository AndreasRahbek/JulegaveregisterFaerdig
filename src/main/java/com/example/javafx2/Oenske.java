package com.example.javafx2;

public class Oenske {
    private int oenskeId;
    private String oenskeNavn;
    private int oenskeAntal;
    private String oenskeLink;
    private boolean oenskeKøbt = false;





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

    public int getoenskeId() {
        return oenskeId;
    }

    public void setoenskeId(int oenskeId) {
        this.oenskeId = oenskeId;
    }

    public String getoenskeNavn() {
        return oenskeNavn;
    }

    public void setoenskeNavn(String oenskeNavn) {
        this.oenskeNavn = oenskeNavn;
    }

    public int getoenskeAntal() {
        return oenskeAntal;
    }

    public void setoenskeAntal(int oenskeAntal) {
        this.oenskeAntal = oenskeAntal;
    }

    public String getoenskeLink() {
        return oenskeLink;
    }

    public void setoenskeLink(String oenskeLink) {
        this.oenskeLink = oenskeLink;
    }

    public boolean isoenskeKøbt() {
        return oenskeKøbt;
    }

    public void setoenskeKøbt(boolean oenskeKøbt) {
        this.oenskeKøbt = oenskeKøbt;
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
