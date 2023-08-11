/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package x;

/**
 *
 * @author Usuario
 */
public class Estudiante extends Persona{
    private String numeroCarnet;
    public Estudiante(String pNombre,String pNumeroCarnet){
        super(pNombre);
        setNumeroCarnet(pNumeroCarnet);
    }
        public void setNumeroCarnet(String pNumeroCarnet){
        numeroCarnet=pNumeroCarnet;
    }
    public String tostring(){
        return "Estudiante "+super.toString()+""+numeroCarnet;
    }
}