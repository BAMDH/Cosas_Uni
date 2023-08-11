/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programa2;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class info implements Serializable{

    private static final long serialVersionUID = 1L;
    private int cs;
    private int s;
    private int m;
    private int h;
    private String nombre;
    public info(int pcs, int ps, int pm,  int ph, String pnombre){
        cs=pcs;
        m=pm;
        h=ph;
        s=ps;
        nombre=pnombre;
    }
    public int getMedicion(){
        int resultado=cs;
        if (m!=0){
            resultado+=m*60*100;
        
        }
        if (s!=0){
            resultado+=s*100;
        
        }
        if (h!=0){
            resultado+=h*60*60*100;
        
        }
        return resultado;
    }
    public String getNombre(){
       
        return nombre;
        
    }
    public String toString(){
        return "Nombre: "+nombre+"\nTiempo: "+h+" : "+m+" : "+s+"\n\n";
    }
    
            
}
