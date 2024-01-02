/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Entidades;


    public class Personal {

        private int id;
        private String nombres;
        private String apellidos;
        private String cargo;
        private String nomUsuario;
        private String contrasenia;
        private boolean disponibilidad;
    
    public Personal(int id, String nombres, String apellidos, String cargo, String nomUsuario, String contrasenia, boolean disponibilidad) {
        
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.nomUsuario = nomUsuario;
        this.contrasenia = contrasenia;
        this.disponibilidad = disponibilidad;
    }

    // getters y setters

    public Personal() {
      
    }

    

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
}
    