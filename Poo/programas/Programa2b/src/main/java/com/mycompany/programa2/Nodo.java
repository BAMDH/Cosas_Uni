/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programa2;

import java.io.Serializable;

/**
 * Nodos con tipos de datos genericos de una lista doblemente enlazada
 */


public class Nodo<T>implements Serializable{
    public T dato;
    public Nodo<T>predecesor;
    public Nodo<T>sucesor;
    Nodo(){
        predecesor = null;
        sucesor = null;
    }
}
    
