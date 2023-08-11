/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa2;

import futoshiki.Futoshiki;
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
    
     public Partida(Futoshiki pFutoshiki, ListaDoblementeEnlazada<String> pPila, ListaDoblementeEnlazada<String> pPila2,ArrayList<Integer> pOrden,int pPosicion){
        futoshiki=pFutoshiki;
        pila=pPila;
        pila2=pPila2;
        orden=pOrden;
        posicion=pPosicion;
    }
    public Futoshiki getFutoshiki(){
        return futoshiki;
    }
    public ListaDoblementeEnlazada<String> getPila(){
        return pila;
    }
    public ListaDoblementeEnlazada<String> getPila2(){
        return pila2;
    }
    public ArrayList<Integer> getOrden(){
        return orden;
    }
    public int getP(){
        return posicion;
    }
    Partida() {
	};
}
