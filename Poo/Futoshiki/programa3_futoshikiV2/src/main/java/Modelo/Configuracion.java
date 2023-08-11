/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Natalia S.R.S
 */
public class Configuracion implements Serializable{

    private static final long serialVersionUID = 1L;
    private int aXa;
    private String nivel;
    private boolean multiNivel;
    private boolean timer;
    private boolean izquierda;
    /**
     * Crea la nueva configuracion
     * @param a Dimension de la matriz
     * @param n nivel del juego
     * @param m multinivel
     */
    public Configuracion(int a, String n, boolean m,boolean i){
        aXa=a;
        nivel=n;
        multiNivel=m;
        izquierda=i;
    }

    public boolean getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }
    
    public void setAXa(int a){
        aXa=a;
    }
    public void setNivel(String n){
        nivel=n;
    }
    public void setMultiNivel(boolean m){
        multiNivel=m;
    }
    public int getAXa(){
        return aXa;
    }
    public String getNivel(){
        return nivel;
    }
    public boolean getMultiNivel(){
        return multiNivel;
    }
}
