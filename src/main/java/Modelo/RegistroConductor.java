/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Entidades.Bus;
import Modelo.Entidades.Conductor;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Julio López
 */
public class RegistroConductor {
    
     private int IDRegC;
    private Conductor conductor;
    private Bus busI;
    private Bus busF;
    private int kmI;
    private int kmF;
    private Time horainicial;
    private Time horafinal;
    private Date fechaI;
    private Date fechaF;
    private String descripRC;
    private boolean fallamecanica;
    
    public RegistroConductor(){}

    public RegistroConductor(int IDRegC, Conductor conductor, Bus busI, Bus busF, int kmI, int kmF,Time horainicial,Time horafinal, Date fechaI, Date fechaF, String descripRC,boolean fallamecanica) {
        this.IDRegC = IDRegC;
        this.conductor = conductor;
        this.busI = busI;
        this.busF = busF;
        this.kmI = kmI;
        this.kmF = kmF;
        this.horainicial=horainicial;
        this.horafinal = horafinal;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.descripRC = descripRC;
        this.fallamecanica = fallamecanica;
    }

    public boolean isFallamecanica() {
        return fallamecanica;
    }

    public void setFallamecanica(boolean fallamecanica) {
        this.fallamecanica = fallamecanica;
    }

    public RegistroConductor(boolean fallamecanica) {
        this.fallamecanica = fallamecanica;
    }

    public Time getHorainicial() {
        return horainicial;
    }

    public void setHorainicial(Time horainicial) {
        this.horainicial = horainicial;
    }

    public Time getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(Time horafinal) {
        this.horafinal = horafinal;
    }

    

    public int getIDRegC() {
        return IDRegC;
    }

    public void setIDRegC(int IDRegC) {
        this.IDRegC = IDRegC;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Bus getBusI() {
        return busI;
    }

    public void setBusI(Bus busI) {
        this.busI = busI;
    }

    public Bus getBusF() {
        return busF;
    }

    public void setBusF(Bus busF) {
        this.busF = busF;
    }

    public int getKmI() {
        return kmI;
    }

    public void setKmI(int kmI) {
        this.kmI = kmI;
    }

    public int getKmF() {
        return kmF;
    }

    public void setKmF(int kmF) {
        this.kmF = kmF;
    }

    public Date getFechaI() {
        return fechaI;
    }

    public void setFechaI(Date fechaI) {
        this.fechaI = fechaI;
    }

    public Date getFechaF() {
        return fechaF;
    }

    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }

    public String getDescripRC() {
        return descripRC;
    }

    public void setDescripRC(String descripRC) {
        this.descripRC = descripRC;
    }
}
