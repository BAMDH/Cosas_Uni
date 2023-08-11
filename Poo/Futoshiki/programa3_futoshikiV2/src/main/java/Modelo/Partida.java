/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Partida implements Serializable{
    private static final long serialVersionUID = 1L;
    private Futoshiki futoshiki;
    private ListaDoblementeEnlazada<String> pila;
    private ListaDoblementeEnlazada<String> pila2;
    private ArrayList<Integer> orden;
    private int posicion;
    private int [] tiempo= new int[4];

    private int [] tiempoT= new int[4];
    private Configuracion confi;
    private String nombre;
    /**
     * Crea una nueva partida para poder guardarla
     * @param pFutoshiki Juego actual
     * @param pPila Pila de jugadas realizadas
     * @param pPila2 Pila de jugadas borradas
     * @param pOrden Orden de las partidas
     * @param pPosicion Número de partida actual
     * @param ptiempo Tiempo actual
     * @param pNombre Nombre del jugador
     */
    public Partida(Futoshiki pFutoshiki, ListaDoblementeEnlazada<String> pPila, ListaDoblementeEnlazada<String> pPila2,ArrayList<Integer> pOrden,int pPosicion,int ptiempo[], String pNombre,Configuracion configuracion,int ptiempoT[]){
        futoshiki=pFutoshiki;
        pila=pPila;
        pila2=pPila2;
        orden=pOrden;
        posicion=pPosicion;
        tiempo= ptiempo;
        nombre = pNombre;
        confi=configuracion;
        tiempoT=ptiempoT;
    }
    
    public int[] getTiempoT() {
        return tiempoT;
    }

    public Configuracion getConfi() {
        return confi;
    }
    /**
     * Obtiene la partida
     * @return Partida actual
     */
    public Futoshiki getFutoshiki(){
        return futoshiki;
    }
    /**
     * Obtiene la pila de movimientos
     * @return Pila de jugadas realizadas
     */
    public ListaDoblementeEnlazada<String> getPila(){
        return pila;
    }
    /**
     * Obtiene la pila de movimientos eliminados
     * @return Pila de jugadas eliminadas
     */
    public ListaDoblementeEnlazada<String> getPila2(){
        return pila2;
    }
    /**
     * Obtiene el orden
     * @return Orden
     */
    public ArrayList<Integer> getOrden(){
        return orden;
    }
    /**
     * Obtiene la posición
     * @return posición
     */
    public int getP(){
        return posicion;
    }
    /**
     * Obtiene nombre del jugador
     * @return nombre
     */
    public String getN(){
        return nombre;
    }
    /**
     * Obtiene el tiempo jugado
     * @return tiempo
     */
    public int[] getT(){
        return tiempo;
    }    
}
