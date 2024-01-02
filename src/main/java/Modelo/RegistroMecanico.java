/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Entidades.Bus;


public class RegistroMecanico {
    private int Id;
    private Bus Bus;
    private int periodoHoras;
    private String estado;
    private String direccion;
    private String suspension;
    private String transmision;
    private String corona;
    private String motor;
    private String comentario;

    public RegistroMecanico() {
    }

    public RegistroMecanico(int Id, Bus Bus, int periodoHoras, String estado, String direccion, String suspension, String transmision, String corona, String motor, String comentario) {
        this.Id = Id;
        this.Bus = Bus;
        this.periodoHoras = periodoHoras;
        this.estado = estado;
        this.direccion = direccion;
        this.suspension = suspension;
        this.transmision = transmision;
        this.corona = corona;
        this.motor = motor;
        this.comentario = comentario;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Bus getBus() {
        return Bus;
    }

    public void setBus(Bus Bus) {
        this.Bus = Bus;
    }

    public int getPeriodoHoras() {
        return periodoHoras;
    }

    public void setPeriodoHoras(int periodoHoras) {
        this.periodoHoras = periodoHoras;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSuspension() {
        return suspension;
    }

    public void setSuspension(String suspension) {
        this.suspension = suspension;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getCorona() {
        return corona;
    }

    public void setCorona(String corona) {
        this.corona = corona;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
