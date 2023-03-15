package com.example.hospital_com;

public class Administrador {
    String idAd, nomAd, apeAd, passAd;

    public Administrador() {
    }

    public Administrador(String idAd, String nomAd, String apeAd, String passAd) {
        this.idAd = idAd;
        this.nomAd = nomAd;
        this.apeAd = apeAd;
        this.passAd = passAd;
    }

    public String getIdAd() {
        return idAd;
    }

    public void setIdAd(String idAd) {
        this.idAd = idAd;
    }

    public String getNomAd() {
        return nomAd;
    }

    public void setNomAd(String nomAd) {
        this.nomAd = nomAd;
    }

    public String getApeAd() {
        return apeAd;
    }

    public void setApeAd(String apeAd) {
        this.apeAd = apeAd;
    }

    public String getPassAd() {
        return passAd;
    }

    public void setPassAd(String passAd) {
        this.passAd = passAd;
    }
}
