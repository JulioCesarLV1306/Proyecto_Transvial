/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Entidades;

/**
 *
 * @author Julio López
 */
public class Conductor extends Personal {

    public Conductor() {
    }
    public Conductor( int id,String nombres, String apellidos, String cargo, String nomUsuario, String contrasenia, boolean disponibilidad) {
        super( id ,nombres, apellidos, cargo, nomUsuario, contrasenia, disponibilidad);
    }
}