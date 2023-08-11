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
public class Tops implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int aXa;
    private String nivel;
    private ArrayList<Info> top;
    public Tops(int a, String b){
        top=new ArrayList<Info>();
        aXa=a;
        nivel=b;
    }

    public int getaXa() {
        return aXa;
    }

    public String getNivel() {
        return nivel;
    }

    public ArrayList<Info> getTop() {
        return top;
    }

    public void setTop(ArrayList<Info> top) {
        this.top = top;
    }
    
}
