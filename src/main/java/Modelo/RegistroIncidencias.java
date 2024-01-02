/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Entidades.Bus;
import Modelo.Entidades.Mecanico;
import java.time.LocalDate;
import java.util.Date;


public class RegistroIncidencias {
    private int ID;
    private Mecanico mecanico;
    private String periodoInforme;
    private Bus bus;
    private Date fecha;
    private String descripcion;
    private int numeroSanja;

   

    public RegistroIncidencias() {
    }

    public RegistroIncidencias(int ID, Mecanico mecanico, String periodoInforme, Bus bus, Date fecha, String descripcion,int numeroSanja) {
        this.ID = ID;
        this.mecanico = mecanico;
        this.periodoInforme = periodoInforme;
        this.bus = bus;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.numeroSanja = numeroSanja;
    }

    public int getNumeroSanja() {
        return numeroSanja;
    }

    public void setNumeroSanja(int numeroSanja) {
        this.numeroSanja = numeroSanja;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public String getPeriodoInforme() {
        return periodoInforme;
    }

    public void setPeriodoInforme(String periodoInforme) {
        this.periodoInforme = periodoInforme;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
}
