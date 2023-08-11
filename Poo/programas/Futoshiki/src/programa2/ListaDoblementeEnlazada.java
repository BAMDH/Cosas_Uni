/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa2;

import java.io.Serializable;

/**
 *
 * Lista doblemente enlazada de objetos genericos
 */


public class ListaDoblementeEnlazada<T> implements Serializable {
private Nodo cabezaLista;
private Nodo colaLista;

    ListaDoblementeEnlazada(){
        cabezaLista = null;
        colaLista = null;
    }
    /**
     * agregar elementos al final de la lista
     */
    public void push(T t){
        Nodo nodo = new Nodo<T>();
        nodo.dato = t;
        
        if (cabezaLista ==null){
            cabezaLista=nodo;
            colaLista = nodo;
        }
        else{
            nodo.predecesor = colaLista;
            colaLista.sucesor = nodo;
            colaLista = nodo;
        }
    }
   
    public Nodo pop(){
        Nodo Aux= colaLista;
        if(Aux !=null){
            if(colaLista.predecesor==null){
                colaLista=null;
                cabezaLista=null;
            }
            else{
                colaLista.predecesor.sucesor=null;
                colaLista=colaLista.predecesor;
            }
            System.out.println("Elemento sacado: " + Aux.dato.toString());
        }
        else{
            System.out.println("Elemento Nulo");
        }
        return Aux;
    }
    public Nodo popLeft(){
        Nodo Aux= cabezaLista;
        if(Aux !=null){
            if (cabezaLista.sucesor==null){
                cabezaLista=null;
                colaLista=null;
            }
            else{
                cabezaLista.sucesor.predecesor=null;
                cabezaLista=cabezaLista.sucesor;
            }
          
        }
        
        return Aux;
        
    }
    public void clear(){      
        Nodo Aux;
        while (cabezaLista!=null){
            Aux=colaLista;
            if(Aux !=null){
                if(colaLista.predecesor==null){
                    colaLista=null;
                    cabezaLista=null;
                }
                else{
                    colaLista.predecesor.sucesor=null;
                    colaLista=colaLista.predecesor;
                }       
            }
        }
     
    }
    public boolean empty(){
        if(cabezaLista==null){
            return true;
        }
        else{
            return false;
        }
    }
    public Nodo peek(){
        if(colaLista!=null){
            return colaLista;
        }
        else{
            return null;
        }
    }
    public Nodo peekleft(){
        if(cabezaLista!=null){
            return cabezaLista;
        }
        else{
            return null;
        }
    }
    /**
     * impresion generica de los elementos de la lista
     */
    public void imprimirLista(){
        Nodo nodo;
        nodo =cabezaLista;
        while (nodo !=null){
        System.out.println(nodo.dato.toString());
        nodo = nodo.sucesor;
        }
    }
}
