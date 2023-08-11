/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package futoshiki;

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
    
    public boolean confirmarCondicion(int pValorado){
          if(condicion.equals("z")||condicion.equals("b")){
              return valor < pValorado;
          }
          else{
              return valor > pValorado;
          }
       
    }
    public boolean confirmarConstante(){
        return constante;
    }
    public int getValor(){
        return valor;
    }
    public String getCondicion(){
        return condicion;
    }
}
