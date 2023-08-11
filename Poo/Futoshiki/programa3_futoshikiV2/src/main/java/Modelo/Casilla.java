/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Casilla  implements Serializable{
    private static final long serialVersionUID = -1755137579432849633L;
    private String condicion;
    private boolean constante;
    private int valor;
    /**
     * Crea una nueva casilla
     */
    public Casilla(){
        constante=false;
        valor=0;
        
    }
    /**
     * Asigna la condicion del componente
     * @param pCondicion Condicion que se va a asignar
     */
    public void setCondicion(String pCondicion){
        condicion = pCondicion;
    }
    /**
     * Asigna el valor de una casilla constante
     * @param pValor Valor de la contante
     */
    public void setConstante(int pValor){
        constante = true;
        setValor(pValor);
    }
    /**
     * Asigna el valor de una casilla casilla
     * @param pValor Valor de la contante
     */
    public void setValor(int pValor){
        valor = pValor;
    }
    /**
     * Compara el valor de la casilla con el valor recibido, utilizando la condición que posee la casilal
     * @param pValorado Valor a comparar
     * @return Cierto si se cumple la condición
     */    
    public boolean confirmarCondicion(int pValorado){
        if(condicion.equals("z")||condicion.equals("b")){
            return valor < pValorado;
        }
        else{
            return valor > pValorado;
        }       
    }
    /**
     * Confirma si es constante
     * @return True si es constante, false en caso contrario
     */
    public boolean confirmarConstante(){
        return constante;
    }
    /**
     * Obtiene el valor de la casilla
     * @return Valor
     */
    public int getValor(){
        return valor;
    }
    /**
     * Obtiene la condición de la casilla
     * @return Condición
     */
    public String getCondicion(){
        return condicion;
    }
}
