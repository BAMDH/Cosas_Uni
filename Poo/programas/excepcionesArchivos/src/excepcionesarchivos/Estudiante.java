/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepcionesarchivos;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Estudiante implements Serializable{
    private String nombre;
    private int carnet;
    public Estudiante(String pNombre,int pCarnet){
        nombre=pNombre;
        carnet=pCarnet;
    }
    public String getNombre(){
        return nombre;
    }
    public int getCarnet(){
        return carnet;
    }
     public String toString(){
        return "\nNombre: "+nombre+"\nCarnet: "+carnet+"\n";
    }
}
