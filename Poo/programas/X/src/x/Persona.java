/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package x;

/**
 *
 * @author Usuario
 */
public class Persona {
    private String nombre;
    
    public Persona(){
    }
    public Persona(String pNombre){
        SetNombre(pNombre);
    }
    public void SetNombre(String pNombre){
        nombre = pNombre;
    }
    public String getNombre(){
        return nombre;
    }
    public String toString(){
        String info = "\nEl nombre es "+nombre+"\n";
        return info;
    }
}
