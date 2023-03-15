package com.example.hospital_com;

public class Medico {
    String idMed, nomMed, apeMed, idGenMed, idEspMed, passMed;

    public Medico() {
    }

    public Medico(String idMed, String nomMed, String apeMed, String idGenMed, String idEspMed, String passMed) {
        this.idMed = idMed;
        this.nomMed = nomMed;
        this.apeMed = apeMed;
        this.idGenMed = idGenMed;
        this.idEspMed = idEspMed;
        this.passMed = passMed;
    }

    public String getIdMed() {
        return idMed;
    }

    public void setIdMed(String idMed) {
        this.idMed = idMed;
    }

    public String getNomMed() {
        return nomMed;
    }

    public void setNomMed(String nomMed) {
        this.nomMed = nomMed;
    }

    public String getApeMed() {
        return apeMed;
    }

    public void setApeMed(String apeMed) {
        this.apeMed = apeMed;
    }

    public String getIdGenMed() {
        return idGenMed;
    }

    public void setIdGenMed(String idGenMed) {
        this.idGenMed = idGenMed;
    }

    public String getIdEspMed() {
        return idEspMed;
    }

    public void setIdEspMed(String idEspMed) {
        this.idEspMed = idEspMed;
    }

    public String getPassMed() {
        return passMed;
    }

    public void setPassMed(String passMed) {
        this.passMed = passMed;
    }
}
