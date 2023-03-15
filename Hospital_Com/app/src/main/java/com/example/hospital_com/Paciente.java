package com.example.hospital_com;

public class Paciente {
    String idPa, nomPa, apePa, idGenPa, fechaNPa, idRhPa, passPa;

    public Paciente() {
    }

    public Paciente(String idPa, String nomPa, String apePa, String idGenPa, String fechaNPa, String idRhPa, String passPa) {
        this.idPa = idPa;
        this.nomPa = nomPa;
        this.apePa = apePa;
        this.idGenPa = idGenPa;
        this.fechaNPa = fechaNPa;
        this.idRhPa = idRhPa;
        this.passPa = passPa;
    }

    public String getIdPa() {
        return idPa;
    }

    public void setIdPa(String idPa) {
        this.idPa = idPa;
    }

    public String getNomPa() {
        return nomPa;
    }

    public void setNomPa(String nomPa) {
        this.nomPa = nomPa;
    }

    public String getApePa() {
        return apePa;
    }

    public void setApePa(String apePa) {
        this.apePa = apePa;
    }

    public String getIdGenPa() {
        return idGenPa;
    }

    public void setIdGenPa(String idGenPa) {
        this.idGenPa = idGenPa;
    }

    public String getFechaNPa() {
        return fechaNPa;
    }

    public void setFechaNPa(String fechaNPa) {
        this.fechaNPa = fechaNPa;
    }

    public String getIdRhPa() {
        return idRhPa;
    }

    public void setIdRhPa(String idRhPa) {
        this.idRhPa = idRhPa;
    }

    public String getPassPa() {
        return passPa;
    }

    public void setPassPa(String passPa) {
        this.passPa = passPa;
    }
}
