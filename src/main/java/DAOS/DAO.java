/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Modelo.Entidades.Personal;
import Modelo.RegistroConductor;
import java.util.List;


public interface DAO<T> {
    T agregar(T entidad);
    int actualizar(T entidad);
    void borrar(T entidad);
    List<T> listarTodos();
    T buscarPorID(int id);

    public RegistroConductor agregarInforme2(RegistroConductor registro2);

   

   
}


